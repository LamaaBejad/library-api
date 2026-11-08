package com.example.libraryapi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookController {

    private final ArrayList<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book("Clean Code"));
        books.add(new Book("Effective Java"));
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return books;
    }     @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
}