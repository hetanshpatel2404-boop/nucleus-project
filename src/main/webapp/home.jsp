<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.nucleus.model.*" %>
<%! String e(String s) { return com.nucleus.util.DBConnection.esc(s); } %>
<%
  User me = (User) session.getAttribute("user");
  List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
<!DOCTYPE html>
<html><head><title>NUCLEUS - Home</title>
<link rel="stylesheet" href="css/style.css"></head>
<body>
<div class="nav">NUCLEUS <span>Hi, <%= e(me.name) %> | <a href="logout">Logout</a></span></div>
<div class="feed">

  <form action="home" method="post" class="card">
    <input type="hidden" name="action" value="post">
    <textarea name="content" placeholder="Share something with NUV students..." required></textarea>
    <button>Post</button>
  </form>

  <% for (Post p : posts) { %>
  <div class="card">
    <b><%= e(p.authorName) %></b> <small><%= p.createdAt %></small>
    <p><%= e(p.content) %></p>

    <form action="home" method="post" class="inline">
      <input type="hidden" name="action" value="like">
      <input type="hidden" name="postId" value="<%= p.id %>">
      <button class="small">Like (<%= p.likeCount %>)</button>
    </form>

    <% for (Comment c : p.comments) { %>
      <div class="comment"><b><%= e(c.authorName) %>:</b> <%= e(c.content) %></div>
    <% } %>

    <form action="home" method="post">
      <input type="hidden" name="action" value="comment">
      <input type="hidden" name="postId" value="<%= p.id %>">
      <input name="content" placeholder="Write a comment..." required>
    </form>
  </div>
  <% } %>
</div>
</body></html>
