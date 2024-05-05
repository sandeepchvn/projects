package com.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

	static Connection con;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qsp", "postgres", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveStudent(Student s) throws SQLException {

		PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?)");
		ps.setInt(1, s.getId());
		ps.setString(2, s.getName());
		ps.setLong(3, s.getPhone());
		ps.executeUpdate();

	}

	public void updateStudentPhone(int id, long phone) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update student set phone=? where id=?");
		ps.setInt(2, id);
		ps.setLong(1, phone);
		ps.executeUpdate();

	}

	public void deleteStudentById(int id) throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from student where id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public Student fetchStudentById(int id) throws SQLException {
		Student s = new Student();
		PreparedStatement ps = con.prepareStatement("select * from student where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setPhone(rs.getLong(3));
		}
		return s;
	}

	public List<Student> fetchAll() throws SQLException {
		List<Student> l = new ArrayList<Student>();
		PreparedStatement ps = con.prepareStatement("select * from student");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Student s = new Student();
			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setPhone(rs.getLong(3));
			l.add(s);
		}
		return l;
	}
}
