package com.example.libraryapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final Library library;

    public BookController(Library library) {
        this.library = library;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
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
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        library.deleteBook(id);
    }
}