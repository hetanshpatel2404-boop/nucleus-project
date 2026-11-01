package com.nucleus.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public int id, likeCount;
    public String authorName, content, createdAt;
    public List<Comment> comments = new ArrayList<>();
}
