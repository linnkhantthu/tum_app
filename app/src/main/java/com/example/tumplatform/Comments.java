package com.example.tumplatform;

public class Comments {
    private author author;
    private String content;
    private String date_posted;
    private int id;
    private int post_id;

    public Comments(com.example.tumplatform.author author, String content, String date_posted, int id, int post_id) {
        this.author = author;
        this.content = content;
        this.date_posted = date_posted;
        this.id = id;
        this.post_id = post_id;
    }

    public com.example.tumplatform.author getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public int getId() {
        return id;
    }

    public int getPost_id() {
        return post_id;
    }
}
