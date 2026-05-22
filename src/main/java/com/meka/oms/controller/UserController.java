package com.meka.oms.controller;

import com.meka.oms.dto.ApiResponse;
import com.meka.oms.dto.UserRequestDTO;
import com.meka.oms.dto.UserResponseDTO;
import com.meka.oms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(
            @Valid @RequestBody UserRequestDTO request
    ) {
        return userService.createUser(request);
    }

    @GetMapping
    public ApiResponse<Page<UserResponseDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        Page<UserResponseDTO> users =
                userService.getAllUsers(page, size);

        return ApiResponse.<Page<UserResponseDTO>>builder()
                .success(true)
                .message("Users fetched successfully")
                .data(users)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseDTO> getUserById(
            @PathVariable Long id
    ) {

        UserResponseDTO user = userService.getUserById(id);

        return ApiResponse.<UserResponseDTO>builder()
                .success(true)
                .message("User fetched successfully")
                .data(user)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO request
    ) {

        UserResponseDTO updatedUser =
                userService.updateUser(id, request);

        return ApiResponse.<UserResponseDTO>builder()
                .success(true)
                .message("User updated successfully")
                .data(updatedUser)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(
            @PathVariable Long id
    ) {

        userService.deleteUser(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("User deleted successfully")
                .data(null)
                .build();
    }
}