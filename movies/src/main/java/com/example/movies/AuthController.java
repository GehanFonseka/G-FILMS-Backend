package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }

        // Hash the password before saving
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword().trim()));

        // Log the hashed password to verify
        System.out.println("Hashed Password: " + user.getPassword());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Find the user by username
        User foundUser = userRepository.findByUsername(user.getUsername());

        // Check if user exists
        if (foundUser == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        // Log the password comparison (hash comparison)
        System.out.println("Entered Password: " + user.getPassword());
        System.out.println("Stored Hashed Password: " + foundUser.getPassword());

        // Validate password using BCrypt
        boolean passwordMatches = new BCryptPasswordEncoder().matches(user.getPassword().trim(), foundUser.getPassword());

        // Log the result of the password comparison
        System.out.println("Password Matches: " + passwordMatches);

        if (!passwordMatches) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        // Generate JWT token if login is successful
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}
