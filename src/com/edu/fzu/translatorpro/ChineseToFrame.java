package com.edu.fzu.translatorpro;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ChineseToFrame extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea getText;
	private JPanel getPanel;
	private JTextArea putText;
	private JPanel putPanel;
    private JComboBox JCB;
	private JButton recordButton;
	private JButton backButton;
	private JCheckBox translateFlag;
	public ChineseToFrame() throws Exception {
		setTitle("��->��");
		setSize(320, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setAlwaysOnTop(true);
		setWindowLayout();
	}

	public void setWindowLayout() throws Exception {
		//Font newfont=new Font("΢���ź�", Font.PLAIN, 16);
		
		
		String[] s={"Ӣ��","����","��������"};
		JCB=new JComboBox(s);//����һ�������˵�
		//JCB.setFont(newfont);
		JCB.setBounds(10, 415, 80, 30);
		add(JCB);
		
		JScrollPane js_get =new JScrollPane();
		getText =new JTextArea(9,35);
		//getText.setFont(newfont);
		getText.setLineWrap(true);//���в����
		getText.setWrapStyleWord(true);
		getPanel=new JPanel();
		getPanel.setBounds(-40, 10, 400, 200);
		add(getPanel);
		getPanel.add(js_get);
		js_get.setViewportView(getText);//���ı������������
		
		JScrollPane js_put =new JScrollPane();
		putText=new JTextArea(9,35);
		//putText.setFont(newfont);
		putText.setLineWrap(true); //�����Զ����й��� 
		putText.setWrapStyleWord(true);// ������в����ֹ���
		putPanel=new JPanel();
		putPanel.setBounds(-40, 210, 400, 200);
		add(putPanel);
		putPanel.add(js_put);
		js_put.setViewportView(putText);
		//putPanel.add(putText);
		setResizable(false);
		
		translateFlag=new JCheckBox();
		translateFlag.setBounds(100,415,30,30);
		add(translateFlag);
		translateFlag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(translateFlag.isSelected())
				{
					translateFlag.setToolTipText("�ֶ�����");
				}
				else {
				    translateFlag.setToolTipText("���а��ȡ");
				}
			}
		});
		
		recordButton=new JButton("��¼");
		backButton=new JButton("����");
		add(recordButton);
		recordButton.setBounds(140, 415, 80, 30);
		add(backButton);
		backButton.setBounds(220, 415, 80, 30);
		
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
		
		getText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String s =(String)JCB.getSelectedItem();//��ȡѡ�еĲ˵�
					String slan = new String();
					if(s=="Ӣ��")
					{
						slan = "en";
					}
					if(s=="����")
					{
						slan = "jp";
					}
					if(s=="��������")
					{
						slan = "spa";
					}
					//����GHCP��ķ������ݣ��������������ݸ��÷���
					String result=GetHtmlContentPro.getTranslation(getText.getText(),slan);
					if(result==null)
					{
						putText.setText("�޷��ҵ���������");
					}
					putText.setText(result);
				} catch (Exception e) { 
					// TODO Auto-generated catch block 
					e.printStackTrace(); 
					putText.setText("����ʧ��"); 
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
     }

	@Override
	public void run() {//��дrun����
		// TODO Auto-generated method stub
		while(true)
		{
			if(!translateFlag.isSelected()){
			   try {
				  String contentString = ClipBoardListener.getClipBoardText();
				  getText.setText(contentString);
			   } catch (Exception e) {
				// TODO Auto-generated catch block
				  e.printStackTrace();
			   }
			}
			try {
				Thread.sleep(1000);//����1��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}