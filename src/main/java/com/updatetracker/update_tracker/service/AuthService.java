package com.updatetracker.update_tracker.service;
import com.updatetracker.update_tracker.dto.*; import com.updatetracker.update_tracker.exception.BadRequestException; import com.updatetracker.update_tracker.model.User; import com.updatetracker.update_tracker.repository.UserRepository; import com.updatetracker.update_tracker.security.JwtUtil;
import org.springframework.security.authentication.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service public class AuthService { private final UserRepository users; private final AuthenticationManager auth; private final JwtUtil jwt;
 public AuthService(UserRepository users,AuthenticationManager auth,JwtUtil jwt){this.users=users;this.auth=auth;this.jwt=jwt;}
 public AuthResponse login(LoginRequest r){auth.authenticate(new UsernamePasswordAuthenticationToken(r.username(),r.password())); User u=users.findByUsername(r.username()).orElseThrow(); return new AuthResponse(jwt.generate(u.getUsername(),u.getRole().name()),u.getUsername(),u.getRole().name());}
}
