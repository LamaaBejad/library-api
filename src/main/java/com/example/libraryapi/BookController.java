package com.example.libraryapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookController {

    private final Library library;

    public BookController(Library library) {
        this.library = library;
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return library.getBooks();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return library.addBook(book);
    }
}