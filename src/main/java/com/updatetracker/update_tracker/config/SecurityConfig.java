package com.updatetracker.update_tracker.config;

import com.updatetracker.update_tracker.model.User; import com.updatetracker.update_tracker.repository.UserRepository; import com.updatetracker.update_tracker.security.JwtFilter;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepository repo){return username->repo.findByUsername(username).map(this::details).orElseThrow(()->new UsernameNotFoundException("User not found"));}
 private UserDetails details(User u) {
  return org.springframework.security.core.userdetails.User
          .withUsername(u.getUsername())
          .password(u.getPassword())
          .roles(u.getRole().name())
          .build();
 }
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http,JwtFilter filter)throws Exception{
  return http.csrf(c->c.disable()).headers(h->h.frameOptions(f->f.sameOrigin())).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/h2-console/**").permitAll().anyRequest().authenticated()).addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class).build();
 }
}
