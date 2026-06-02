package com.distraction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.distraction.model.Student;
import com.distraction.util.DBConnection;

public class StudentDao {

public boolean registerStudent(Student s) {

    boolean status = false;

    try {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO student(name, email, password) VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, s.getName());
        ps.setString(2, s.getEmail());
        ps.setString(3, s.getPassword());

        int rows = ps.executeUpdate();

        if (rows > 0) {
            status = true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return status;
}

// Login Student

public int loginStudent(String email, String password) {

    int studentId = -1;

    try {

        Connection con = DBConnection.getConnection();

        System.out.println("Database Connection = " + con);

        String sql =
                "SELECT id FROM student WHERE email=? AND password=?";

        PreparedStatement ps = con.prepareStatement(sql);

        System.out.println("Executing Query...");
        System.out.println("Email = " + email);
        System.out.println("Password = " + password);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            System.out.println("User Found In Database");

            studentId = rs.getInt("id");

            System.out.println("Student ID From DB = "
                               + studentId);

        } else {

            System.out.println("No User Found In Database");
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return studentId;
}


}
