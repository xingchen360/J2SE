package com.noteshare.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DbConnection {
	private static Log log = LogFactory.getLog(DbConnection.class);
	private static final Properties prop = new Properties();

	// 定义一个用于放置数据库连接的局部线程变量（使每个线程都拥有自己的连接）
	private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();
	static {
		try {
			// 加载数据库连接信息
			InputStream in = DbConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(in);
			String driver = prop.getProperty("Driver");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			log.info(e.getMessage());
		} catch (IOException e) {
			log.info(e.getMessage());
		}
	}

	private DbConnection() {
		super();
	}

	public static Connection getConn() {
		Connection conn = connContainer.get();
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
						prop.getProperty("password"));
				connContainer.set(conn);
			}
		} catch (SQLException e) {
			log.info("获取数据库连接失败：" + e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return conn;
	}

	public static void closeConnection() {
		Connection conn = connContainer.get();
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			log.info("关闭数据库连接失败：" + e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			connContainer.remove();
		}
	}

	public static void main(String[] args) {
		getConn();
		closeConnection();
	}
}
