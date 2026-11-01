package com.nucleus.servlet;

import com.nucleus.dao.UserDAO;
import com.nucleus.model.User;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User u = new UserDAO().login(req.getParameter("email"), req.getParameter("password"));
        if (u != null) {
            req.getSession().setAttribute("user", u);   // remember who is logged in
            res.sendRedirect("home");
        } else {
            res.sendRedirect("login.jsp?msg=Wrong email or password");
        }
    }
}
