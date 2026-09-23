package com.updatetracker.update_tracker.dto;
import com.updatetracker.update_tracker.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreateUserRequest(@NotBlank String username,@NotBlank String password,@NotNull Role role) {}
