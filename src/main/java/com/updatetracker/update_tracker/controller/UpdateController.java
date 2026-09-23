package com.updatetracker.update_tracker.controller;

import com.updatetracker.update_tracker.dto.CreateUpdateRequest;
import com.updatetracker.update_tracker.dto.UpdateResponse;
import com.updatetracker.update_tracker.service.UpdateService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/updates")
public class UpdateController {

    private final UpdateService service;

    public UpdateController(UpdateService service) {
        this.service = service;
    }

    @PostMapping
    public UpdateResponse create(
            Authentication authentication,
            @Valid @RequestBody CreateUpdateRequest request) {

        return service.create(
                authentication.getName(),
                request
        );
    }

    @GetMapping("/mine")
    public List<UpdateResponse> mine(Authentication authentication) {

        return service.mine(
                authentication.getName()
        );
    }

    @GetMapping("/search")
    public List<UpdateResponse> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {

        return service.search(
                username,
                project,
                from,
                to
        );
    }
}