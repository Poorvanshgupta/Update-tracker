package com.updatetracker.update_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true, length=100) private String username;
    @JsonIgnore @Column(nullable=false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private Role role;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_projects", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="project_id"))
    private Set<Project> projects = new HashSet<>();
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getUsername(){return username;} public void setUsername(String username){this.username=username;}
    public String getPassword(){return password;} public void setPassword(String password){this.password=password;}
    public Role getRole(){return role;} public void setRole(Role role){this.role=role;}
    public Set<Project> getProjects(){return projects;} public void setProjects(Set<Project> projects){this.projects=projects;}
}
