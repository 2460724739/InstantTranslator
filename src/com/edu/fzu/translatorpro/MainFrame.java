package com.edu.fzu.translatorpro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setTitle("Instant Translator");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		Init();
	}

	public void Init() {
		
		JButton b0=new JButton("英->中");
		b0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FreeTranslationFrame freeTranslationFrame;
				try {
					freeTranslationFrame = new FreeTranslationFrame();
					freeTranslationFrame.setVisible(true);
					Thread thread=new Thread(freeTranslationFrame);
					thread.start();
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JButton b1= new JButton("多外语->中文");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ToChineseFrame toChineseFrame=new ToChineseFrame();
					toChineseFrame.setVisible(true);
					Thread t = new Thread(toChineseFrame);
					t.start();
					setVisible(false);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		JButton b2= new JButton("中文->多外语");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					ChineseToFrame chineseToFrame=new ChineseToFrame();
					chineseToFrame.setVisible(true);
					Thread t = new Thread(chineseToFrame);
					t.start();
					setVisible(false);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JButton b3=new JButton("添加单词~");
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TwoWordsOneDay twoWordsOneDay=new TwoWordsOneDay();
				twoWordsOneDay.setVisible(true);
				setVisible(false);
			}
		});
		JButton b4=new JButton("复习单词~");
		b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ReviewWords reviewWords=new ReviewWords();
				reviewWords.setVisible(true);
				setVisible(false);
			}
		});
		JLabel label1=new JLabel("以下翻译截取网页源代码。");
		JLabel label2=new JLabel("以下翻译调用百度翻译API。");
		add(b0);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(label1);
		add(label2);
		//add(label3);
		label1.setBounds(90, 5, 550, 30);
		label2.setBounds(90, 90, 520, 30);
		//label3.setBounds(5, 100, 400, 30);
		b0.setBounds(135, 40, 100, 30);
		b1.setBounds(115, 130, 150, 30);
		b2.setBounds(115, 170, 150, 30);
		b3.setBounds(130, 230,120, 30);
		b4.setBounds(130, 270, 120, 30);
		setResizable(false);
	}
}
