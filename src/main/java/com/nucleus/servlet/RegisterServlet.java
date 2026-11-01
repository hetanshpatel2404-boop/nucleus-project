package com.nucleus.servlet;

import com.nucleus.dao.UserDAO;
import com.nucleus.model.User;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User u = new User();
        u.name = req.getParameter("name");
        u.email = req.getParameter("email");
        u.password = req.getParameter("password");
        u.course = req.getParameter("course");
        u.bio = req.getParameter("bio");
        if (new UserDAO().register(u)) res.sendRedirect("login.jsp?msg=Registered! Please login");
        else res.sendRedirect("register.jsp?msg=Email already used");
    }
}
