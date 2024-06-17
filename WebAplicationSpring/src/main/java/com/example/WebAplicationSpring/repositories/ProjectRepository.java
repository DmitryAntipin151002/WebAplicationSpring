package com.example.WebAplicationSpring.repositories;

import com.example.WebAplicationSpring.entities.Project;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CRUD operations for Project
    public int create(Project project) {
        return jdbcTemplate.update("INSERT INTO projects (name, description) VALUES (?, ?)", project.getName(), project.getDescription());
    }

    public List<Project> findAll() {
        return jdbcTemplate.query("SELECT * FROM projects", new BeanPropertyRowMapper<>(Project.class));
    }

    public Project findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM projects WHERE id = ?", new BeanPropertyRowMapper<>(Project.class), id);
    }

    public int update(Project project) {
        return jdbcTemplate.update("UPDATE projects SET name = ?, description = ? WHERE id = ?", project.getName(), project.getDescription(), project.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM projects WHERE id = ?", id);
        }
    }