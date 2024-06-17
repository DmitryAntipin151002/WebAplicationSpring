package com.example.WebAplicationSpring.repositories;

import com.example.WebAplicationSpring.entities.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CRUD operations for Task
    public int create(Task task) {
        return jdbcTemplate.update("INSERT INTO tasks (title, description, user_id) VALUES (?, ?, ?)", task.getTitle(), task.getDescription(), task.getUserId());
    }

    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks", new BeanPropertyRowMapper<>(Task.class));
    }

    public Task findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tasks WHERE id = ?", new BeanPropertyRowMapper<>(Task.class), id);
    }

    public int update(Task task) {
        return jdbcTemplate.update("UPDATE tasks SET title = ?, description = ?, user_id = ? WHERE id = ?", task.getTitle(), task.getDescription(), task.getUserId(), task.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id);
    }
}