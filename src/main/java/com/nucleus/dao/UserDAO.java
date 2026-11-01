package com.nucleus.dao;

import com.nucleus.model.User;
import com.nucleus.util.DBConnection;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    // Save a new student. Password is stored as a hash (not plain text).
    public boolean register(User u) {
        String sql = "INSERT INTO users(name,email,password,course,bio) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.name);
            ps.setString(2, u.email);
            ps.setString(3, BCrypt.hashpw(u.password, BCrypt.gensalt()));
            ps.setString(4, u.course);
            ps.setString(5, u.bio);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Returns the user if email and password match, else null
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
                User u = new User();
                u.id = rs.getInt("id");
                u.name = rs.getString("name");
                u.email = rs.getString("email");
                u.course = rs.getString("course");
                u.bio = rs.getString("bio");
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
