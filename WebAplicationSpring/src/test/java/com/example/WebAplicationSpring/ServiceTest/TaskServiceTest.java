package com.example.WebAplicationSpring.ServiceTest;

import com.example.WebAplicationSpring.dto.TaskDTO;
import com.example.WebAplicationSpring.entities.Task;
import com.example.WebAplicationSpring.repositories.TaskRepository;
import com.example.WebAplicationSpring.service.TaskService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task1;
    private Task task2;
    private TaskDTO taskDTO1;
    private TaskDTO taskDTO2;

    @Before
    public void setUp() {
        // Initialize mock data
        task1 = new Task(1L, "Task 1", "Description 1", 1L);
        task2 = new Task(2L, "Task 2", "Description 2", 2L);

        taskDTO1 = new TaskDTO(1L, "Task 1", "Description 1", 1L);
        taskDTO2 = new TaskDTO(2L, "Task 2", "Description 2", 2L);
    }

    @Test
    public void testGetAllTasks() {
        // Mock repository to return a list of tasks
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        when(taskRepository.findAll()).thenReturn(tasks);

        // Call service method
        List<TaskDTO> taskDTOs = taskService.getAllTasks();

        // Verify the result
        assertEquals(2, taskDTOs.size());
        assertEquals(taskDTO1.getTitle(), taskDTOs.get(0).getTitle());
        assertEquals(taskDTO2.getTitle(), taskDTOs.get(1).getTitle());
    }

    @Test
    public void testGetTaskById() {
        // Mock repository to return a task
        when(taskRepository.findById(1L)).thenReturn(task1);

        // Call service method
        TaskDTO taskDTO = taskService.getTaskById(1L);

        // Verify the result
        assertEquals(taskDTO1.getTitle(), taskDTO.getTitle());
        assertEquals(taskDTO1.getDescription(), taskDTO.getDescription());
        assertEquals(taskDTO1.getUserId(), taskDTO.getUserId());
    }

    @Test
    public void testCreateTask() {
        // Create a taskDTO
        TaskDTO newTaskDTO = new TaskDTO(null, "New Task", "New Description", 3L);

        // Call service method
        taskService.createTask(newTaskDTO);

        // Verify that repository method was called with correct parameters
        verify(taskRepository, times(1)).create(any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        // Create a taskDTO
        TaskDTO updatedTaskDTO = new TaskDTO(null, "Updated Task", "Updated Description", 4L);

        // Call service method
        taskService.updateTask(1L, updatedTaskDTO);

        // Verify that repository method was called with correct parameters
        verify(taskRepository, times(1)).update(any(Task.class));
    }

    @Test
    public void testDeleteTask() {
        // Call service method
        taskService.deleteTask(1L);

        // Verify that repository method was called with correct parameters
        verify(taskRepository, times(1)).delete(eq(1L));
    }
}

