package com.samsung.libraryandroid.domain;

public class Comment {

    private int id;

    private String content;

    public Comment(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
