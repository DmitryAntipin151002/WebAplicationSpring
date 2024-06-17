package com.example.WebAplicationSpring.service;
import com.example.WebAplicationSpring.dto.ProjectDTO;
import com.example.WebAplicationSpring.entities.Project;
import com.example.WebAplicationSpring.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        return convertToDTO(projectRepository.findById(id));
    }

    public void createProject(ProjectDTO projectDTO) {
        projectRepository.create(convertToEntity(projectDTO));
    }

    public void updateProject(Long id, ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        project.setId(id);
        projectRepository.update(project);
    }

    public void deleteProject(Long id) {
        projectRepository.delete(id);
    }

    private ProjectDTO convertToDTO(Project project) {
        return new ProjectDTO(project.getId(), project.getName(), project.getDescription());
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        return new Project(projectDTO.getId(), projectDTO.getName(), projectDTO.getDescription(), new ArrayList<>());
    }
}
