package com.example.WebAplicationSpring.service;

import com.example.WebAplicationSpring.dto.TaskDTO;
import com.example.WebAplicationSpring.entities.Task;
import com.example.WebAplicationSpring.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return convertToDTO(taskRepository.findById(id));
    }

    public void createTask(TaskDTO taskDTO) {
        taskRepository.create(convertToEntity(taskDTO));
    }

    public void updateTask(Long id, TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        task.setId(id);
        taskRepository.update(task);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getUserId());
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(), taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getUserId());
    }
}
