package com.edu.fzu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	 String JDBC_DRIVER="com.mysql.jdbc.Driver";//找到referenced libriries第一个下面的driver.class copyxxx
    static String DB_URL="jdbc:mysql://localhost:3306/words?useUnicode=true&charactorEncoding=UTF-8";
    static String USER="root";
    static String PASS="root";
    static Connection conn=null;//import第二个Java.sql
    public static Connection getConn(){//数据库连接的函数，静态函数
   	 if(conn==null){  //如果第一次调用
   		  try {
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	 }
   	 return conn;//返回连接
   }
}
