package com.example.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryapiApplication.class, args);
	}
		@Bean
		CommandLineRunner seedBooks(BookRepository repo) {
			return args -> {
				if (repo.count() == 0) {
					repo.save(new Book("Clean Code", "Robert C. Martin"));
					repo.save(new Book("Effective Java", "Joshua Bloch"));
					repo.save(new Book("Head First Java", "Kathy Sierra & Bert Bates"));
					repo.save(new Book("Spring in Action", "Craig Walls"));
					repo.save(new Book("Refactoring", "Martin Fowler"));
					repo.save(new Book("The Pragmatic Programmer", "Andrew Hunt & David Thomas"));
					repo.save(new Book("Design Patterns", "Erich Gamma et al."));
					repo.save(new Book("Atomic Habits", "James Clear"));
					repo.save(new Book("الأيام", "طه حسين"));
					repo.save(new Book("مقدمة ابن خلدون", "ابن خلدون"));
				}
			};

		}
}