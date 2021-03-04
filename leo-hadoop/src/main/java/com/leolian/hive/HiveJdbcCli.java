package com.leolian.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveJdbcCli {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive2://192.168.0.177:10000/datax";
	private static String user = "hive";
	private static String password = "";
	private static String sql = "";
	private static ResultSet res;
	private static final Logger log = LoggerFactory.getLogger(HiveJdbcCli.class);

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConn();
			//System.out.println("Connection: "+conn);
			stmt = conn.createStatement();
			
			String tableName = "person";
			
			// �ڶ���:�����ھʹ���
			createTable(stmt, tableName);
			
			// ������:�鿴�����ı�
			showTables(stmt, tableName);

			// ִ��describe table����
			describeTables(stmt, tableName);

			// ִ��load data into table����
			loadData(stmt, tableName);

			// ִ�� select * query ����
			selectData(stmt, tableName);

			// ִ�� regular hive query ͳ�Ʋ���
			countData(stmt, tableName);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error(driverName + " not found!", e);
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Connection error!", e);
			System.exit(1);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void countData(Statement stmt, String tableName) throws SQLException {
		sql = "select count(1) from " + tableName;
		System.out.println("Running:" + sql);
		res = stmt.executeQuery(sql);
		System.out.println("ִ�С�regular hive query�����н��:");
		while (res.next()) {
			System.out.println("count ------>" + res.getString(1));
		}
	}

	private static void selectData(Statement stmt, String tableName) throws SQLException {
		sql = "select * from " + tableName;
		System.out.println("Running:" + sql);
		res = stmt.executeQuery(sql);
		System.out.println("ִ�� select * query ���н��:");
		while (res.next()) {
			System.out.println(res.getInt(1) + "\t" + res.getString(2));
		}
	}

	private static void loadData(Statement stmt, String tableName) throws SQLException {
		String filepath = "'/apps/test-data/person.txt'"; // �����ϵ�����
		sql = "load data local inpath '" + filepath + "' into table " + tableName;
		System.out.println("Running:" + sql);
		res = stmt.executeQuery(sql);
	}

	private static void describeTables(Statement stmt, String tableName) throws SQLException {
		sql = "describe " + tableName;
		System.out.println("Running:" + sql);
		res = stmt.executeQuery(sql);
		System.out.println("ִ�� describe table ���н��:");
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}
	}

	private static void showTables(Statement stmt, String tableName) throws SQLException {
		sql = "show tables '" + tableName + "'";
		System.out.println("Running:" + sql);
		res = stmt.executeQuery(sql);
		System.out.println("ִ�� show tables ���н��:");
		if (res.next()) {
			System.out.println(res.getString(1));
		}
	}

	private static void createTable(Statement stmt, String tableName) throws SQLException {
		sql = "create table if not exists " + tableName + " (pid int, pname string)  row format delimited fields terminated by '\t'";
		stmt.executeUpdate(sql);
	}


	private static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

}