package com.example.libraryapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private final Library library;
    private final GeminiService gemini;
    private final boolean demoMode = "true".equals(System.getenv("DEMO_MODE"));

    public BookController(Library library, GeminiService gemini) {
        this.library = library;
        this.gemini = gemini;
    }

    @GetMapping("/config")
    public Map<String, Boolean> config() {
        return Map.of("demo", demoMode);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return library.getBooks();
    }

    @GetMapping("/books/mine")
    public List<Book> myBooks(Principal principal) {
        return library.getBooksBorrowedBy(requireUser(principal));
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        blockInDemo();
        return library.addBook(book);
    }

    @PutMapping("/books/{title}/borrow")
    public String borrowBook(@PathVariable String title, Principal principal) {
        return library.borrowBook(title, requireUser(principal));
    }

    @PutMapping("/books/{title}/return")
    public String returnBook(@PathVariable String title, Principal principal) {
        return library.returnBook(title, requireUser(principal));
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        blockInDemo();
        library.deleteBook(id);
    }

    @GetMapping("/books/{id}/summary")
    public Map<String, String> summary(@PathVariable Long id) {
        Book book = library.getBook(id);
        String text = gemini.summarize(book.getTitle(), book.getAuthor());
        return Map.of("title", book.getTitle(), "summary", text);
    }

    private String requireUser(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please log in");
        }
        return principal.getName();
    }

    private void blockInDemo() {
        if (demoMode) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Disabled in demo mode");
        }
    }
}