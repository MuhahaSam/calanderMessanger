package com.messageCalender.project.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messageCalender.project.config.SecurityConfig;
import com.messageCalender.project.user.dto.CreateUserDto;
import com.messageCalender.project.user.entities.UserEntity;
import com.messageCalender.project.user.exceptions.UserAlreadyExistsException;
import com.messageCalender.project.user.exceptions.UserNotFoundException;
import com.messageCalender.project.user.mapper.UserMapper;
import com.messageCalender.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;

    public void create(CreateUserDto userDto) throws JsonProcessingException {
        try {
            log.info("Creating user from DTO: {}", objectMapper.writeValueAsString(userDto));
        } catch (Exception e) {
            log.warn("Failed to log DTO as JSON: {}", e.getMessage());
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            log.warn("User with email {} already exists", userDto.getEmail());
            throw new UserAlreadyExistsException(userDto.getEmail());
        }

        UserEntity user = userMapper.toEntity(userDto);
        log.info("Mapped user entity: {}", objectMapper.writeValueAsString(user));
        user.hashPassword(securityConfig.getSalt());

        userRepository.save(user);
        // log.info("Successfully saved user: {}", savedUser);
        // return savedUser;
    }

    public UserEntity findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User not found with email: {}", email);
                    return new UserNotFoundException(email);
                });
    }
}