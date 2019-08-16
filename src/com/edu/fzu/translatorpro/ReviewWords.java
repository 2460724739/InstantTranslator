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
    	setTitle("��ϰһ�°�~");
    	setSize(440,710);
    	setLocationRelativeTo(null);
    	setLayout(null);
    	Init();
	}

	private void Init() {
		/*
		 * �����JLabel�Ļ�ȷʵ��֪��Ҫ�������ٸ�����Ϊ����Ҫ�����ܹ��ж��ٸ����ʻ�ȡCOUNT����
		 * Ȼ��Ҫ��forѭ�������㹻���JLabel��λ��Ҫע��ڷ�
		 */
		 
		DefaultTableModel dtm =new DefaultTableModel(){//����һ��Ĭ�ϱ��ģ��
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		dtm.addColumn("����");//row���� column���� �������
		dtm.addColumn("����");
		dtm.addColumn("�뷨");
		Object[] obj =new Object[3];//����һ������Ϊ3��������
		
		reviewTable =new JTable(dtm);//��Ĭ�ϱ��ģ�ʹ������
		reviewTable.setRowHeight(40);//�����и߶�
		//reviewTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black, 1));
		//����meaning��idea���ܻ�ܳ���������Ҫ��һ��ˮƽ���������鿴����Ȼ�����Ĳ��ֻ���...�����ص�
		reviewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//����ˮƽ������
		reviewTable.getColumnModel().getColumn(0).setPreferredWidth(100);//���õ�һ�п��
		reviewTable.getColumnModel().getColumn(1).setPreferredWidth(120);//���õڶ��п��
		reviewTable.getColumnModel().getColumn(2).setPreferredWidth(180);//���õ����п��
		JScrollPane jsp =new JScrollPane(reviewTable);//��������������
		jsp.setBounds(10, 10, 400, 600);
		add(jsp);
		
		String sql="select word,meaning,idea from words";
		try {
			Statement statement=DataBase.getConn().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				obj[0]=rs.getString(1);//���������һ��Ϊrs����ĵ�һ��
				obj[1]=rs.getString(2);//��������ڶ���Ϊrs����ĵڶ���
				obj[2]=rs.getString(3);//�������������Ϊrs����ĵ�����
				dtm.addRow(obj);//��obj��ӽ�dtm
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton deleteButton=new JButton("ɾ��");
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
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
					}
				}
			}
		});
		
		JButton backButton=new JButton("����");
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
