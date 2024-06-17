package com.example.WebAplicationSpring.controllers;

import com.example.WebAplicationSpring.dto.ProjectDTO;
import com.example.WebAplicationSpring.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public void createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.createProject(projectDTO);
    }

    @PutMapping("/{id}")
    public void updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        projectService.updateProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
