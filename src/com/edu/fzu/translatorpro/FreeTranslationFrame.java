package com.edu.fzu.translatorpro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.edu.fzu.translatorpro.ClipBoardListener;
import com.edu.fzu.translatorpro.GetHtmlContent;

public class FreeTranslationFrame extends JFrame implements Runnable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea getText;
	private JPanel getPanel;
	private JTextArea putText;
	private JPanel putPanel;
	private JButton recordButton;
	private JButton backButton;
	private JCheckBox translateFlag;

	public FreeTranslationFrame() throws Exception {
		setName("Instant Translator");
		setSize(320, 500);
		setLocationRelativeTo(null);
	    setLayout(null);
		setAlwaysOnTop(true);
		setWindowLayout();
	}

	public void setWindowLayout() throws Exception {
		//Font newfont=new Font("微软雅黑", Font.PLAIN, 14);
		//setLayout(new GridLayout(2, 1));
		
		JScrollPane js_get =new JScrollPane();
		getText =new JTextArea(9,35);
		//getText.setFont(newfont);
		getText.setLineWrap(true);
		getText.setWrapStyleWord(true);
		getPanel=new JPanel();
		getPanel.setBounds(-40, 10, 400, 200);
		add(getPanel);
		getPanel.add(js_get);
		js_get.setViewportView(getText);
		
		JScrollPane js_put =new JScrollPane();
		putText=new JTextArea(9,35);
		//putText.setFont(newfont);
		putText.setLineWrap(true); //激活自动换行功能 
		putText.setWrapStyleWord(true);// 激活断行不断字功能
		putPanel=new JPanel();
		putPanel.setBounds(-40, 210, 400, 200);
		add(putPanel);
		putPanel.add(js_put);
		js_put.setViewportView(putText);
		//putPanel.add(putText);
		
		translateFlag=new JCheckBox();
		translateFlag.setBounds(30,415,30,30);
		add(translateFlag);
		translateFlag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(translateFlag.isSelected())
				{
					translateFlag.setToolTipText("手动输入");
				}
				else {
				    translateFlag.setToolTipText("剪切板获取");
				}
			}
		});
		setResizable(false);//设置用户可否调大小
		recordButton=new JButton("记录");
		backButton=new JButton("返回");
		add(recordButton);
		recordButton.setBounds(100, 415, 80, 30);
		add(backButton);
		backButton.setBounds(200, 415, 80, 30);
		
        recordButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TwoWordsOneDay twoWordsOneDay=new TwoWordsOneDay();
				twoWordsOneDay.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainFrame mainFrame=new MainFrame();
				mainFrame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		getText.getDocument().addDocumentListener(new DocumentListener() {//添加文件监听
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String result=GetHtmlContent.getTranslation(getText.getText().replace(" ","%20").trim());
					if(result==null)
					{
						result="翻译失败1";
					}
					putText.setText(result);
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					putText.setText("翻译失败2");
				}
				 
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(!translateFlag.isSelected()){
				try {
					String content=ClipBoardListener.getClipBoardText();
					getText.setText(getSimpleWord(content));
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 public static String getSimpleWord(String content) 
	 {//去掉切板里面的一些特殊字符
	        return content.replace(".", "")
	        		.replace(",", "")
	                .replace("'", "")
	                .replace(":", "")
	                .replace(";", "")
	                .replace("-", "")
	                .replace("?", "")
	                .replace("/", "").trim();
	 }
	
}