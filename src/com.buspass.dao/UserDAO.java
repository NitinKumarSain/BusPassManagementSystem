package com.buspass.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.buspass.model.User;

public class UserDAO {

    // 🔹 Database connection method
    public static Connection getConnection() {
        Connection con = null;
        try {
            // MySQL JDBC Driver load
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/buspass_db?useSSL=false&serverTimezone=UTC",
                "root",
                "PASSWORD_HERE"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // 🔹 Register user method
    public static int registerUser(User u) {
        int status = 0;
        try {
            Connection con = getConnection();

            String sql = "INSERT INTO users(name, email, mobile, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getMobile());
            ps.setString(4, u.getPassword());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
