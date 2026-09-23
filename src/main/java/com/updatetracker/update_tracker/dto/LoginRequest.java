package com.updatetracker.update_tracker.dto;
import jakarta.validation.constraints.NotBlank;
public record LoginRequest(@NotBlank String username,@NotBlank String password) {}
