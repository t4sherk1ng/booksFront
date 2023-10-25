package com.samsung.libraryandroid.rest;

import com.samsung.libraryandroid.domain.Book;

public interface LibraryApi {

    void fillBook();

    void fillGenre();

    void fillAuthor();

    void newBook(Book book);

    void updateBook(int id, String newBookName, String newAuthorName, String newGenreName);

    void deleteBook(int id);

}
