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
					repo.save(new Book("Clean Code"));
					repo.save(new Book("Effective Java"));
					repo.save(new Book("Head First Java"));
					repo.save(new Book("Spring in Action"));
				}
			};

		}
}