package com.eagle.interview.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class DBUtil {
	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);


	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		Connection conn = local.get();
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://192.168.56.131:3306/db2?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&allowMultiQueries=true",
						"root",
						"123456");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			local.set(conn);
		}

		return conn;
	}

	public static void close() {
		Connection conn = local.get();
		if (conn != null) {
			local.remove();
			conn = null;
		}

	}
	public static void closeResource(ResultSet rs, Statement stmt, Connection conn) {
		closeAutoCloseableResource(rs);
		closeAutoCloseableResource(stmt);
		closeAutoCloseableResource(conn);
	}

	private static void closeAutoCloseableResource(AutoCloseable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
}
