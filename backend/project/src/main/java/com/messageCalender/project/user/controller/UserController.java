package com.messageCalender.project.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.messageCalender.project.user.dto.create.CreateUserDto;
import com.messageCalender.project.user.dto.read.UserResponseDto;
import com.messageCalender.project.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "API for user management")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User with this email already exists")
    })
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateUserDto userDto) throws JsonProcessingException {
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get user by ID", description = "Returns user details with messengers by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully"),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable BigInteger id) {
        UserResponseDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }
}