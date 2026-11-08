package com.example.libraryapi;
import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloController {
        @GetMapping("/hello")
        public String hello() {
            return "Hello Lama, my library API is running!";
        }
    }
