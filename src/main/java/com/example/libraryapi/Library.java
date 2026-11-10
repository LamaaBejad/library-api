package com.example.libraryapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Library {

    private final BookRepository bookRepository;

    public Library(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksBorrowedBy(String username) {
        return bookRepository.findAll().stream()
                .filter(b -> username.equals(b.getBorrowedBy()))
                .toList();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public String borrowBook(String title, String username) {
        Optional<Book> found = bookRepository.findByTitleIgnoreCase(title);
        if (found.isEmpty()) {
            return "Book not found";
        }
        Book book = found.get();
        if (!book.isAvailable()) {
            return "Book is already borrowed";
        }
        book.setAvailable(false);
        book.setBorrowedBy(username);
        bookRepository.save(book);
        return "Book borrowed";
    }

    public String returnBook(String title, String username) {
        Optional<Book> found = bookRepository.findByTitleIgnoreCase(title);
        if (found.isEmpty()) {
            return "Book not found";
        }
        Book book = found.get();
        if (book.isAvailable()) {
            return "Book was not borrowed";
        }
        if (book.getBorrowedBy() != null && !book.getBorrowedBy().equals(username)) {
            return "You can only return books you borrowed";
        }
        book.setAvailable(true);
        book.setBorrowedBy(null);
        bookRepository.save(book);
        return "Book returned";
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}