package com.samsung.libraryandroid.fakedb;

import com.samsung.libraryandroid.domain.Author;
import com.samsung.libraryandroid.domain.Book;
import com.samsung.libraryandroid.domain.Genre;

import java.util.ArrayList;
import java.util.List;

public class LibraryFakeDb {

    private LibraryFakeDb(){}

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public static final List<Genre> GENRE_LIST = new ArrayList<>();

    public static final List<Author> AUTHOR_LIST = new ArrayList<>();
}
