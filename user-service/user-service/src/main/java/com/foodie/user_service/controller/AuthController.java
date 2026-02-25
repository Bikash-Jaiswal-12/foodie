package com.foodie.user_service.controller;

import com.foodie.user_service.dto.JwtResponse;
import com.foodie.user_service.dto.LoginRequest;
import com.foodie.user_service.dto.SingupRequest;
import com.foodie.user_service.entity.RoleEnum;
import com.foodie.user_service.entity.UserEntity;
import com.foodie.user_service.repository.UserRepository;
import com.foodie.user_service.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SingupRequest request){
        Optional<UserEntity> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(" User already exists");
        }

        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());  // Normal users only!
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        // Save the new user
        UserEntity savedUser = userRepository.save(user);

        return ResponseEntity.ok(" User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("User not found");
        }

        UserEntity user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(" Invalid credentials");
        }

        // Generate token with claims
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());


        return ResponseEntity.ok(new JwtResponse(token));
    }

}
