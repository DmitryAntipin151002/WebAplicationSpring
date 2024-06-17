package com.example.WebAplicationSpring.repositories;

import com.example.WebAplicationSpring.entities.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CRUD operations for User
    public int create(User user) {
        return jdbcTemplate.update("INSERT INTO users (email, password_hash) VALUES (?, ?)", user.getEmail(), user.getPasswordHash());
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new BeanPropertyRowMapper<>(User.class), id);
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE users SET email = ?, password_hash = ? WHERE id = ?", user.getEmail(), user.getPasswordHash(), user.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}