package com.propertylisting.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propertylisting.app.dto.Response;
import com.propertylisting.app.model.User;
import com.propertylisting.app.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Response<User> login(@RequestBody User user) {
        try {
            User _response = authService.login(user.getUsername(), user.getPassword());
            if (_response == null) {
                throw new IllegalStateException("Invalid credentials or User not found");
            }
            return new Response<>(true, "Login successful ", _response);
        } catch (IllegalStateException ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @PostMapping("/signup")
    public Response<String> signup(@RequestBody User user) {
        try {
            String message = authService.signup(user);
            return new Response<>(true, message, null);
        } catch (IllegalStateException ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }
}
