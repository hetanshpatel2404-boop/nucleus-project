package com.nucleus.dao;

import com.nucleus.model.*;
import com.nucleus.util.DBConnection;
import java.sql.*;
import java.util.*;

public class PostDAO {

    public void addPost(int userId, String content) {
        run("INSERT INTO posts(user_id,content) VALUES(?,?)", userId, content);
    }

    public void addComment(int postId, int userId, String content) {
        run("INSERT INTO comments(post_id,user_id,content) VALUES(?,?,?)", postId, userId, content);
    }

    // Like a post. If already liked, "INSERT IGNORE" does nothing.
    public void like(int postId, int userId) {
        run("INSERT IGNORE INTO likes(post_id,user_id) VALUES(?,?)", postId, userId);
    }

    // Get all posts (newest first) with their likes and comments
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT p.id, p.content, p.created_at, u.name, "
                   + "(SELECT COUNT(*) FROM likes l WHERE l.post_id=p.id) AS likes "
                   + "FROM posts p JOIN users u ON p.user_id=u.id ORDER BY p.id DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Post p = new Post();
                p.id = rs.getInt("id");
                p.content = rs.getString("content");
                p.createdAt = rs.getString("created_at");
                p.authorName = rs.getString("name");
                p.likeCount = rs.getInt("likes");
                p.comments = getComments(con, p.id);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Comment> getComments(Connection con, int postId) throws SQLException {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT c.content, u.name FROM comments c JOIN users u ON c.user_id=u.id WHERE c.post_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.authorName = rs.getString("name");
                c.content = rs.getString("content");
                list.add(c);
            }
        }
        return list;
    }

    // Small helper: runs an insert with int/String values
    private void run(String sql, Object... values) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) ps.setObject(i + 1, values[i]);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
