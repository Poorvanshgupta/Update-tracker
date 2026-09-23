package com.updatetracker.update_tracker.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
public record CreateUpdateRequest(@NotNull LocalDate updateDate,@NotNull Long projectId,@NotBlank @Size(max=4000) String content) {}
