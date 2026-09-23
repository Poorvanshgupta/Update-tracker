package com.updatetracker.update_tracker.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;
public record UpdateResponse(Long id,LocalDate updateDate,String content,String username,String projectName,LocalDateTime createdAt,LocalDateTime updatedAt) {}
