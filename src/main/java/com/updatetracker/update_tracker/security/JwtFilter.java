package com.updatetracker.update_tracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

 private final JwtUtil jwt;
 private final UserDetailsService users;

 public JwtFilter(JwtUtil jwt, UserDetailsService users) {
  this.jwt = jwt;
  this.users = users;
 }

 @Override
 protected void doFilterInternal(
         HttpServletRequest req,
         HttpServletResponse res,
         FilterChain chain)
         throws ServletException, IOException {

  String header = req.getHeader("Authorization");

  if (header != null && header.startsWith("Bearer ")) {

   String token = header.substring(7);

   if (jwt.valid(token)
           && SecurityContextHolder.getContext().getAuthentication() == null) {

    String username = jwt.username(token);

    var userDetails = users.loadUserByUsername(username);

    var authentication =
            new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

    SecurityContextHolder
            .getContext()
            .setAuthentication(authentication);
   }
  }

  chain.doFilter(req, res);
 }
}