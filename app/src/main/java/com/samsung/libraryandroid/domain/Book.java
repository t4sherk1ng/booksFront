package com.samsung.libraryandroid.domain;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    private int id;

    private String name;

    private Author author;

    private Genre genre;

    private List<Comment> commentList;



    public Book(int id, String name, Author author, Genre genre, List<Comment> commentList) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.commentList = commentList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", commentList=" + commentList +
                '}';
    }
}
