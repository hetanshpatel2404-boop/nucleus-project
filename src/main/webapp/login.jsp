<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html><head><title>NUCLEUS - Login</title>
<link rel="stylesheet" href="css/style.css"></head>
<body><div class="box">
  <h2>NUCLEUS Login</h2>
  <% if (request.getParameter("msg") != null) { %>
    <p class="msg"><%= com.nucleus.util.DBConnection.esc(request.getParameter("msg")) %></p>
  <% } %>
  <form action="login" method="post">
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <button>Login</button>
  </form>
  <a href="register.jsp">New student? Register</a>
</div></body></html>
