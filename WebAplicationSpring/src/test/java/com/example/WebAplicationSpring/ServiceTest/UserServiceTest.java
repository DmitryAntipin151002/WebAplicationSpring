package com.example.WebAplicationSpring.ServiceTest;

import com.example.WebAplicationSpring.dto.UserDTO;
import com.example.WebAplicationSpring.entities.User;
import com.example.WebAplicationSpring.repositories.UserRepository;
import com.example.WebAplicationSpring.service.UserService;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;
    private UserDTO userDTO1;
    private UserDTO userDTO2;

    @Before
    public void setUp() {
        // Initialize mock data
        user1 = new User(1L, "user1@example.com", "password1");
        user2 = new User(2L, "user2@example.com", "password2");

        userDTO1 = new UserDTO(1L, "user1@example.com", "password1");
        userDTO2 = new UserDTO(2L, "user2@example.com", "password2");
    }

    @Test
    public void testGetAllUsers() {
        // Mock repository to return a list of users
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);

        // Call service method
        List<UserDTO> userDTOs = userService.getAllUsers();

        // Verify the result
        assertEquals(2, userDTOs.size());
        assertEquals(userDTO1.getEmail(), userDTOs.get(0).getEmail());
        assertEquals(userDTO2.getEmail(), userDTOs.get(1).getEmail());
    }

    @Test
    public void testGetUserById() {
        // Mock repository to return a user
        when(userRepository.findById(1L)).thenReturn(user1);

        // Call service method
        UserDTO userDTO = userService.getUserById(1L);

        // Verify the result
        assertEquals(userDTO1.getEmail(), userDTO.getEmail());
        assertEquals(userDTO1.getPasswordHash(), userDTO.getPasswordHash());
    }

    @Test
    public void testCreateUser() {
        // Create a userDTO
        UserDTO newUserDTO = new UserDTO(null, "newuser@example.com", "newpassword");

        // Call service method
        userService.createUser(newUserDTO);

        // Verify that repository method was called with correct parameters
        verify(userRepository, times(1)).create(any(User.class));
    }

    @Test
    public void testUpdateUser() {
        // Create a userDTO
        UserDTO updatedUserDTO = new UserDTO(null, "updateduser@example.com", "updatedpassword");

        // Call service method
        userService.updateUser(1L, updatedUserDTO);

        // Verify that repository method was called with correct parameters
        verify(userRepository, times(1)).update(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // Call service method
        userService.deleteUser(1L);

        // Verify that repository method was called with correct parameters
        verify(userRepository, times(1)).delete(eq(1L));
    }
}

