package com.example.WebAplicationSpring.ServiceTest;
import com.example.WebAplicationSpring.dto.ProjectDTO;
import com.example.WebAplicationSpring.entities.Project;
import com.example.WebAplicationSpring.repositories.ProjectRepository;
import com.example.WebAplicationSpring.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project1;
    private Project project2;
    private ProjectDTO projectDTO1;
    private ProjectDTO projectDTO2;

    @Before
    public void setUp() {
        // Initialize mock data
        project1 = new Project(1L, "Project 1", "Description 1", new ArrayList<>());
        project2 = new Project(2L, "Project 2", "Description 2", new ArrayList<>());

        projectDTO1 = new ProjectDTO(1L, "Project 1", "Description 1");
        projectDTO2 = new ProjectDTO(2L, "Project 2", "Description 2");
    }

    @Test
    public void testGetAllProjects() {
        // Mock repository to return a list of projects
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        when(projectRepository.findAll()).thenReturn(projects);

        // Call service method
        List<ProjectDTO> projectDTOs = projectService.getAllProjects();

        // Verify the result
        assertEquals(2, projectDTOs.size());
        assertEquals(projectDTO1.getName(), projectDTOs.get(0).getName());
        assertEquals(projectDTO2.getName(), projectDTOs.get(1).getName());
    }

    @Test
    public void testGetProjectById() {
        // Mock repository to return a project
        when(projectRepository.findById(1L)).thenReturn(project1);

        // Call service method
        ProjectDTO projectDTO = projectService.getProjectById(1L);

        // Verify the result
        assertEquals(projectDTO1.getName(), projectDTO.getName());
        assertEquals(projectDTO1.getDescription(), projectDTO.getDescription());
    }

    @Test
    public void testCreateProject() {
        // Create a projectDTO
        ProjectDTO newProjectDTO = new ProjectDTO(null, "New Project", "New Description");

        // Call service method
        projectService.createProject(newProjectDTO);

        // Verify that repository method was called with correct parameters
        verify(projectRepository, times(1)).create(any(Project.class));
    }

    @Test
    public void testUpdateProject() {
        // Create a projectDTO
        ProjectDTO updatedProjectDTO = new ProjectDTO(null, "Updated Project", "Updated Description");

        // Call service method
        projectService.updateProject(1L, updatedProjectDTO);

        // Verify that repository method was called with correct parameters
        verify(projectRepository, times(1)).update(any(Project.class));
    }

    @Test
    public void testDeleteProject() {
        // Call service method
        projectService.deleteProject(1L);

        // Verify that repository method was called with correct parameters
        verify(projectRepository, times(1)).delete(eq(1L));
    }
}

