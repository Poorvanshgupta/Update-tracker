package com.updatetracker.update_tracker.dto;
import jakarta.validation.constraints.NotBlank;
public record CreateProjectRequest(@NotBlank String name,String description) {}
