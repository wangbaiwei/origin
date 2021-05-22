package com.harmong.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

public class DbUtil {
	
	
	private InputStream stream;
	private FileInputStream in;
	PreparedStatement prestmt;
	Connection conn;
	
	/**
	 * 获取连接
	 */
	public Connection getConnection() {
		InputStream resourceAsStream = DbUtil.class.getClassLoader().getResourceAsStream("config/config.properties");
		Properties prop = new Properties();
		try {
			prop.load(resourceAsStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			String jdbcDriver = prop.getProperty("jdbcDriver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			Class.forName(jdbcDriver);
			return DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return null;
			
	}
	
	/**
	 * 数据更新
	 */
	public int update(String sql, Object...obj) {

		conn = getConnection();
		try {
			prestmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prestmt.setObject(i + 1, obj[i]);
			}
			return prestmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return -1;
		
	}
	
	/**
	 * 数据查询
	 */
	public List<Map<String, Object>> query(String sql) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			conn = getConnection();
			prestmt = conn.prepareStatement(sql);
			ResultSet result = prestmt.executeQuery();
			ResultSetMetaData resultMetaData = result.getMetaData();
			int colNum = resultMetaData.getColumnCount();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < colNum; i++) {
					map.put(resultMetaData.getColumnName(i), result.getObject(i));
				}
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
		
	}
	
	/**
	 * 关闭资源
	 */
	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (prestmt != null) {
				prestmt.close();
			}
			if (in != null) {
				in.close();
			}
			if (stream != null) {
				stream.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
