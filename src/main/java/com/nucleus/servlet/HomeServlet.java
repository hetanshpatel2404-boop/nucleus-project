package com.nucleus.servlet;

import com.nucleus.dao.PostDAO;
import com.nucleus.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// GET  /home  -> show all posts
// POST /home  -> new post, comment or like (decided by the "action" field)
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    PostDAO dao = new PostDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("posts", dao.getAllPosts());
        req.getRequestDispatcher("home.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User me = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");

        if ("post".equals(action)) {
            dao.addPost(me.id, req.getParameter("content"));
        } else if ("comment".equals(action)) {
            dao.addComment(Integer.parseInt(req.getParameter("postId")), me.id, req.getParameter("content"));
        } else if ("like".equals(action)) {
            dao.like(Integer.parseInt(req.getParameter("postId")), me.id);
        }
        res.sendRedirect("home");
    }
}
