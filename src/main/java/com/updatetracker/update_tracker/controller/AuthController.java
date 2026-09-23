package com.updatetracker.update_tracker.controller;
import com.updatetracker.update_tracker.dto.*; import com.updatetracker.update_tracker.service.AuthService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") public class AuthController {private final AuthService service; public AuthController(AuthService s){service=s;} @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r){return service.login(r);}}
