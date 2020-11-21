package com.eagle.interview.security;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLInjection {
	public static void main(String[] args) {
//		delStudent();
		queryAll();
	}

	//字符型注入
	public static void queryAll() {
		String psw = "'1' or '1=1'";
		String sql1 = "select * from student where name= 'zhangsan' and password="+psw;

		String name = "'zhangsan' or '1=1' UNION SELECT 1,2,GROUP_CONCAT(table_name) FROM information_schema.`TABLES` WHERE table_schema=DATABASE()" ;
		String sql2 = "select id, name,deptNo from student where name="+name;
		query(sql2);
	}

	//字符型注入
	public static void delStudent() {
		String name = "%\';delete from student where id=4 or \'\'= \' ";
//		name = "%\';-- \' "; //correct

		String sql ="SELECT * FROM `student` where name like \'" + name + "%\';";
		query(sql);

	}

	public static void query(String sql){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		System.out.println("sql: " + sql);
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			System.out.println("id" + " " + "name"
					+ " " + "deptNo");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name")
						+ " " + rs.getString("deptNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, psmt, conn);
			DBUtil.close();
		}
	}

	public static List<Student> getResult(String sql){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		List<Student> students = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(String.valueOf(rs.getInt("id")));
				s.setName(rs.getString("name"));
				s.setDepNo(rs.getString("deptNo"));
				s.setCardno(rs.getString("cardno"));
				students.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, psmt, conn);
			DBUtil.close();
		}
		return students;
	}

	public static String updateResult(String sql){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return "updated fail";
		} finally {
			DBUtil.closeResource(rs, psmt, conn);
			DBUtil.close();
		}
		return "updated success";
	}

	public static List<Student> getByName(String sql){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		List<Student> students = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(String.valueOf(rs.getInt("id")));
				s.setName(rs.getString("name"));
				s.setPsw(rs.getString("password"));
				students.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, psmt, conn);
			DBUtil.close();
		}
		return students;
	}
}
