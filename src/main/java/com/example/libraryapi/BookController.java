package com.example.libraryapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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
    @PutMapping("/books/{title}/borrow")
    public String borrowBook(@PathVariable String title) {
        return library.borrowBook(title);
    }

    @PutMapping("/books/{title}/return")
    public String returnBook(@PathVariable String title) {
        return library.returnBook(title);
    }
}