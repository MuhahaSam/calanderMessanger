package com.messageCalender.project.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messageCalender.project.config.SecurityConfig;
import com.messageCalender.project.user.dto.create.CreateUserDto;
import com.messageCalender.project.user.dto.read.UserResponseDto;
import com.messageCalender.project.user.entities.UserEntity;
import com.messageCalender.project.user.exceptions.UserAlreadyExistsException;
import com.messageCalender.project.user.exceptions.UserNotFoundException;
import com.messageCalender.project.user.mapper.UserMapper;
import com.messageCalender.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

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
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException(userDto.getEmail());
        }

        UserEntity user = userMapper.toEntity(userDto);
        user.hashPassword(securityConfig.getSalt());

        userRepository.save(user);
    }

    public UserEntity findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Transactional(readOnly = true)
    public UserResponseDto getById(BigInteger id) {
        log.info("Finding user by id: {}", id);
        UserEntity user = userRepository.findByIdWithMessengers(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        return userMapper.toDto(user);
    }
}