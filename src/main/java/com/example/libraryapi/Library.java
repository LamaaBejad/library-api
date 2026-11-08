package com.example.libraryapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Library {

    private final ArrayList<Book> books = new ArrayList<>();

    public Library() {
        books.add(new Book("Clean Code"));
        books.add(new Book("Effective Java"));
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }
}