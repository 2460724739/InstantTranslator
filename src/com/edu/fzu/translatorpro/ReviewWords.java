package com.edu.fzu.translatorpro;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.edu.fzu.database.DataBase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReviewWords extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable reviewTable;

	public ReviewWords() {
		// TODO Auto-generated constructor stub
    	setTitle("复习一下吧~");
    	setSize(440,710);
    	setLocationRelativeTo(null);
    	setLayout(null);
    	Init();
	}

	private void Init() {
		/*
		 * 如果以JLabel的话确实不知道要创建多少个，因为首先要遍历总共有多少个单词获取COUNT总数
		 * 然后要用for循环创建足够多的JLabel，位置要注意摆放
		 */
		 
		DefaultTableModel dtm =new DefaultTableModel(){//创建一个默认表格模型
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		dtm.addColumn("单词");//row是行 column是列 添加三列
		dtm.addColumn("释义");
		dtm.addColumn("想法");
		Object[] obj =new Object[3];//创建一个容量为3对象数组
		
		reviewTable =new JTable(dtm);//以默认表格模型创建表格
		reviewTable.setRowHeight(40);//设置行高度
		//reviewTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black, 1));
		//由于meaning和idea可能会很长，所以需要有一个水平滑动条来查看，不然超出的部分会以...来隐藏掉
		reviewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//设置水平滑动条
		reviewTable.getColumnModel().getColumn(0).setPreferredWidth(100);//设置第一列宽度
		reviewTable.getColumnModel().getColumn(1).setPreferredWidth(120);//设置第二列宽度
		reviewTable.getColumnModel().getColumn(2).setPreferredWidth(180);//设置第三列宽度
		JScrollPane jsp =new JScrollPane(reviewTable);//将表格加入滚动面板
		jsp.setBounds(10, 10, 400, 600);
		add(jsp);
		
		String sql="select word,meaning,idea from words";
		try {
			Statement statement=DataBase.getConn().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				obj[0]=rs.getString(1);//对象数组第一个为rs结果的第一列
				obj[1]=rs.getString(2);//对象数组第二个为rs结果的第二列
				obj[2]=rs.getString(3);//对象数组第三个为rs结果的第三列
				dtm.addRow(obj);//将obj添加进dtm
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton deleteButton=new JButton("删除");
		deleteButton.setBounds(120, 616, 80, 30);
		add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {	
			private int row;
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				reviewTable.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						
						if(reviewTable.getSelectedRowCount()==1)
						{
							row=reviewTable.getSelectedRow();
						}
					}
					public void mouseExited(MouseEvent arg0) {}
					public void mousePressed(MouseEvent arg0){}
					public void mouseReleased(MouseEvent arg0) {}	
					public void mouseEntered(MouseEvent arg0) {}
				});
				if(reviewTable.isRowSelected(row)==true)
				{
					String s1=(String)reviewTable.getValueAt(reviewTable.getSelectedRow(), 0);
					String s2=(String)reviewTable.getValueAt(reviewTable.getSelectedRow(), 1);
					String s3=(String)reviewTable.getValueAt(reviewTable.getSelectedRow(), 2);
					String sql="delete from words where `word` ='"+s1+"' " +
							"and `meaning` = '"+s2+"' and `idea` = '"+s3+"' " ;
					try {
						Statement statement=DataBase.getConn().createStatement();
						statement.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "删除成功");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});
		
		JButton backButton=new JButton("返回");
		backButton.setBounds(240, 616, 80, 30);
		add(backButton);
		backButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainFrame mainFrame=new MainFrame();
				mainFrame.setVisible(true);
				dispose();
			}
		});
	}
}
