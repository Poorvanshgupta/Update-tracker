package com.updatetracker.update_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="work_updates", indexes={@Index(name="idx_update_date", columnList="update_date"), @Index(name="idx_update_user", columnList="user_id"), @Index(name="idx_update_project", columnList="project_id")})
public class WorkUpdate {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="update_date", nullable=false) private LocalDate updateDate;
    @Column(nullable=false, length=4000) private String content;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id") private User user;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="project_id") private Project project;
    @Column(nullable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime updatedAt;
    @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
    @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public LocalDate getUpdateDate(){return updateDate;} public void setUpdateDate(LocalDate v){updateDate=v;}
    public String getContent(){return content;} public void setContent(String v){content=v;}
    public User getUser(){return user;} public void setUser(User v){user=v;}
    public Project getProject(){return project;} public void setProject(Project v){project=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
