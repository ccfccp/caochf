package com.caochf.spider.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 数据库连接类.
 * @author caochf
 *
 */
public class DaoUtil {
	private static Connection conn ;
	
	/**
	 * 获取数据库连接【注意：谁获取的谁关闭，此处不提供自动关闭功能！！！】.
	 * @param dbDriver
	 * @param dbUrl
	 * @param userName
	 * @param pwd
	 * @return 数据库连接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConnection(String dbDriver,String dbUrl,String userName,String pwd) throws ClassNotFoundException, SQLException {
		try {
			if(conn==null){
				Class.forName(dbDriver);
				conn=DriverManager.getConnection(dbUrl,userName,pwd);
			}
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e){
			throw new RuntimeException("获取数据库连接异常！", e);
		}
		
		return conn;
	}
}
