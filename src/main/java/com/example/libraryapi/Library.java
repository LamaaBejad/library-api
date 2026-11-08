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

    private Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public String borrowBook(String title) {
        Book book = findBook(title);
        if (book == null) {
            return "Book not found";
        }
        if (!book.isAvailable()) {
            return "Book is already borrowed";
        }
        book.setAvailable(false);
        return "Book borrowed";
    }

    public String returnBook(String title) {
        Book book = findBook(title);
        if (book == null) {
            return "Book not found";
        }
        if (book.isAvailable()) {
            return "Book was not borrowed";
        }
        book.setAvailable(true);
        return "Book returned";
    }
}