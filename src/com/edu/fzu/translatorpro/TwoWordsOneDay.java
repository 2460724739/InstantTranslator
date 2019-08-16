package com.edu.fzu.translatorpro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.edu.fzu.database.DataBase;
//import com.mysql.jdbc.Statement;
import java.sql.Statement;
public class TwoWordsOneDay extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel wordLabel;
	private JTextField wordField;
	private JLabel meaningLabel;
	private JTextField meaningField;
	private JLabel ideaLabel;
	private JTextArea ideaArea;
	private JButton saveButton;
	private JButton backButton;

	public TwoWordsOneDay() {
		// TODO Auto-generated constructor stub
    	   setTitle("һ�������ͺ�~");
    	   setSize(400,400);
    	   setLocationRelativeTo(null);
    	   setLayout(null);
    	   init();
	}

	private void init() {
		// TODO Auto-generated method stub
		/*
		 * ����һ���������
		 * ����һ����˼���
		 * ����һ���뷨���
		 * ����һ��
		 * */
		setResizable(false);
		wordLabel =new JLabel("����:");//JLabel/JTetxField�Ⱥ��澭��Ҫ���õ������
		//��Ҫ��������������棬��Ҫ�����ڷ����������������Ҫ���õ�ʱ���Ҫ��������ĳ�Final����������
		//һ����local variable һ����field�������ܴ󡣶�����JPanel�����ں��治����õ���������ֱ���ڷ����ﶨ��
		wordField=new JTextField(12);
		JPanel wordPanel=new JPanel();
		wordPanel.setBounds(85, 20, 200, 50);
		this.add(wordPanel);
		wordPanel.add(wordLabel);
		wordPanel.add(wordField);
		
		meaningLabel=new JLabel("��˼:");
		meaningField=new JTextField(12);
		JPanel meaningPanel=new JPanel();
		meaningPanel.setBounds(85,70, 200, 50);
		this.add(meaningPanel);
		meaningPanel.add(meaningLabel);
		meaningPanel.add(meaningField);
		
		ideaLabel=new JLabel("����������������ʵ��κ��뷨/ע�⡤����");
		ideaArea=new JTextArea(5,18);
		ideaArea.setLineWrap(true);
		ideaArea.setWrapStyleWord(true);
		ideaArea.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		JPanel ideaPanel=new JPanel();
		ideaPanel.setBounds(40, 120, 300, 150);
		this.add(ideaPanel);
		ideaPanel.add(ideaLabel);
		ideaPanel.add(ideaArea);
		
		saveButton=new JButton("����");
		backButton=new JButton("����");
		add(saveButton);
		saveButton.setBounds(100, 280, 80, 30);
		add(backButton);
		backButton.setBounds(200, 280, 80, 30);
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String word=wordField.getText();
				String meaning=meaningField.getText();
				String idea=ideaArea.getText();
				if(word.length()!=0&&meaning.length()!=0){
				String sql="insert into words value('"+word+"','"+meaning+"','"+idea+"')";
				Statement statement;
				try {
					statement = DataBase.getConn().createStatement();
					statement.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainFrame mainFrame=new MainFrame();
				mainFrame.setVisible(true);
				setVisible(false);
			}
		});
	}
}











