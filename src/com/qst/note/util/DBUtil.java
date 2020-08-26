package com.qst.note.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static String dbName="note";//数据库名
	static String name="root";//数据库用户名
	static String pass="root";//数据库密码
	
//	public static void main(String[] args) {
//		Connection c=getConnection();
//		try {
//			Statement st=(Statement)c.createStatement();
//			st.execute("insert into user(name,pass) value('zhangsan','123')");
//			close(c,st,null);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static Connection getConnection(){
		Connection c=null;
		try {
			//加载MySQL驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			//创建数据库连接
			c=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/note?serverTimezone=UTC",name,pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	public static void close(Connection c,Statement stat,ResultSet rs){
		if(rs !=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stat !=null){
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(c !=null){
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

