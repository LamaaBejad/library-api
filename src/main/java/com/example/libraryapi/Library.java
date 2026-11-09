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

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public String borrowBook(String title) {
        Optional<Book> found = bookRepository.findByTitleIgnoreCase(title);
        if (found.isEmpty()) {
            return "Book not found";
        }
        Book book = found.get();
        if (!book.isAvailable()) {
            return "Book is already borrowed";
        }
        book.setAvailable(false);
        bookRepository.save(book);
        return "Book borrowed";
    }

    public String returnBook(String title) {
        Optional<Book> found = bookRepository.findByTitleIgnoreCase(title);
        if (found.isEmpty()) {
            return "Book not found";
        }
        Book book = found.get();
        if (book.isAvailable()) {
            return "Book was not borrowed";
        }
        book.setAvailable(true);
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