<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html><head><title>NUCLEUS - Register</title>
<link rel="stylesheet" href="css/style.css"></head>
<body><div class="box">
  <h2>Create Profile</h2>
  <% if (request.getParameter("msg") != null) { %>
    <p class="msg"><%= com.nucleus.util.DBConnection.esc(request.getParameter("msg")) %></p>
  <% } %>
  <form action="register" method="post">
    <input name="name" placeholder="Full name" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <input name="course" placeholder="Course (e.g. B.Tech CSE)">
    <input name="bio" placeholder="Short bio">
    <button>Register</button>
  </form>
  <a href="login.jsp">Already have an account? Login</a>
</div></body></html>
