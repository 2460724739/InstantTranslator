package com.edu.fzu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	 String JDBC_DRIVER="com.mysql.jdbc.Driver";//�ҵ�referenced libriries��һ�������driver.class copyxxx
    static String DB_URL="jdbc:mysql://localhost:3306/words?useUnicode=true&charactorEncoding=UTF-8";
    static String USER="root";
    static String PASS="root";
    static Connection conn=null;//import�ڶ���Java.sql
    public static Connection getConn(){//���ݿ����ӵĺ�������̬����
   	 if(conn==null){  //�����һ�ε���
   		  try {
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	 }
   	 return conn;//��������
   }
}
