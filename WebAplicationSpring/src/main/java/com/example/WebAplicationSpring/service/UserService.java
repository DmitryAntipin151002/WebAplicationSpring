package com.example.WebAplicationSpring.service;

import com.example.WebAplicationSpring.dto.UserDTO;
import com.example.WebAplicationSpring.entities.User;
import com.example.WebAplicationSpring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return convertToDTO(userRepository.findById(id));
    }

    public void createUser(UserDTO userDTO) {
        userRepository.create(convertToEntity(userDTO));
    }

    public void updateUser(Long id, UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user.setId(id);
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getPasswordHash());
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getEmail(), userDTO.getPasswordHash());
    }
}
