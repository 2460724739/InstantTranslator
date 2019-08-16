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
    	   setTitle("一天两个就好~");
    	   setSize(400,400);
    	   setLocationRelativeTo(null);
    	   setLayout(null);
    	   init();
	}

	private void init() {
		// TODO Auto-generated method stub
		/*
		 * 定义一个单词面板
		 * 定义一个意思面板
		 * 定义一个想法面板
		 * 定义一个
		 * */
		setResizable(false);
		wordLabel =new JLabel("单词:");//JLabel/JTetxField等后面经常要调用的组件，
		//需要定义在类的最上面，不要定义在方法里，这样其他方法要调用的时候就要将该组件改成Final，这样不好
		//一个是local variable 一个是field两个差别很大。而至于JPanel这种在后面不会调用的组件则可以直接在方法里定义
		wordField=new JTextField(12);
		JPanel wordPanel=new JPanel();
		wordPanel.setBounds(85, 20, 200, 50);
		this.add(wordPanel);
		wordPanel.add(wordLabel);
		wordPanel.add(wordField);
		
		meaningLabel=new JLabel("意思:");
		meaningField=new JTextField(12);
		JPanel meaningPanel=new JPanel();
		meaningPanel.setBounds(85,70, 200, 50);
		this.add(meaningPanel);
		meaningPanel.add(meaningLabel);
		meaningPanel.add(meaningField);
		
		ideaLabel=new JLabel("——·关于这个单词的任何想法/注解·——");
		ideaArea=new JTextArea(5,18);
		ideaArea.setLineWrap(true);
		ideaArea.setWrapStyleWord(true);
		ideaArea.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		JPanel ideaPanel=new JPanel();
		ideaPanel.setBounds(40, 120, 300, 150);
		this.add(ideaPanel);
		ideaPanel.add(ideaLabel);
		ideaPanel.add(ideaArea);
		
		saveButton=new JButton("保存");
		backButton=new JButton("返回");
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
					JOptionPane.showMessageDialog(null, "添加成功");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "添加失败");
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











