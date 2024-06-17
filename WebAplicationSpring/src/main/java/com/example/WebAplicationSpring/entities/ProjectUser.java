package com.example.WebAplicationSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUser {
    private Long projectId; // Foreign key to Project
    private Long userId;    // Foreign key to User
}