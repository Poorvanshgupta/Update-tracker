package com.updatetracker.update_tracker.repository;
import com.updatetracker.update_tracker.model.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectRepository extends JpaRepository<Project,Long>{Optional<Project> findByName(String name); boolean existsByName(String name);}
