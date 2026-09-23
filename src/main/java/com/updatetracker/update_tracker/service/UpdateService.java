package com.updatetracker.update_tracker.service;

import com.updatetracker.update_tracker.dto.CreateUpdateRequest;
import com.updatetracker.update_tracker.dto.UpdateResponse;
import com.updatetracker.update_tracker.exception.BadRequestException;
import com.updatetracker.update_tracker.exception.ResourceNotFoundException;
import com.updatetracker.update_tracker.model.Project;
import com.updatetracker.update_tracker.model.Role;
import com.updatetracker.update_tracker.model.User;
import com.updatetracker.update_tracker.model.WorkUpdate;
import com.updatetracker.update_tracker.repository.ProjectRepository;
import com.updatetracker.update_tracker.repository.UserRepository;
import com.updatetracker.update_tracker.repository.WorkUpdateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UpdateService {

 private final WorkUpdateRepository updates;
 private final UserRepository users;
 private final ProjectRepository projects;

 public UpdateService(
         WorkUpdateRepository updates,
         UserRepository users,
         ProjectRepository projects) {

  this.updates = updates;
  this.users = users;
  this.projects = projects;
 }

 @Transactional
 public UpdateResponse create(
         String username,
         CreateUpdateRequest request) {

  User user = users.findByUsername(username)
          .orElseThrow(() ->
                  new ResourceNotFoundException("User not found"));

  Project project = projects.findById(request.projectId())
          .orElseThrow(() ->
                  new ResourceNotFoundException("Project not found"));

  if (!user.getProjects().contains(project)
          && user.getRole() != Role.ADMIN) {

   throw new BadRequestException(
           "User is not assigned to this project");
  }

  WorkUpdate update = new WorkUpdate();

  update.setUser(user);
  update.setProject(project);
  update.setUpdateDate(request.updateDate());
  update.setContent(request.content());

  return to(updates.save(update));
 }

 public List<UpdateResponse> search(
         String username,
         String project,
         LocalDate from,
         LocalDate to) {

  return updates.search(
                  blank(username),
                  blank(project),
                  from,
                  to)
          .stream()
          .map(this::to)
          .toList();
 }

 public List<UpdateResponse> mine(String username) {

  User user = users.findByUsername(username)
          .orElseThrow(() ->
                  new ResourceNotFoundException("User not found"));

  return updates
          .findByUserIdOrderByUpdateDateDesc(user.getId())
          .stream()
          .map(this::to)
          .toList();
 }

 private String blank(String value) {
  return value == null || value.isBlank()
          ? null
          : value;
 }

 private UpdateResponse to(WorkUpdate update) {

  return new UpdateResponse(
          update.getId(),
          update.getUpdateDate(),
          update.getContent(),
          update.getUser().getUsername(),
          update.getProject().getName(),
          update.getCreatedAt(),
          update.getUpdatedAt()
  );
 }
}
