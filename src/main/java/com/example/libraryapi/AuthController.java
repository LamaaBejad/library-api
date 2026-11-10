package com.example.libraryapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class AuthController {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    record RegisterRequest(String username, String password) {
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest req) {
        String username = req.username() == null ? "" : req.username().trim();
        String password = req.password() == null ? "" : req.password();

        if (username.length() < 3 || username.length() > 30) {
            return error(HttpStatus.BAD_REQUEST, "Username must be 3-30 characters");
        }
        if (password.length() < 6) {
            return error(HttpStatus.BAD_REQUEST, "Password must be at least 6 characters");
        }
        if (users.existsByUsername(username)) {
            return error(HttpStatus.CONFLICT, "Username already taken");
        }

        users.save(new AppUser(username, encoder.encode(password)));
        return ResponseEntity.ok(Map.of("message", "Account created"));
    }

    @GetMapping("/me")
    public Map<String, Object> me(Principal principal) {
        if (principal == null) {
            return Map.of("loggedIn", false);
        }
        return Map.of("loggedIn", true, "username", principal.getName());
    }

    private ResponseEntity<Map<String, String>> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}