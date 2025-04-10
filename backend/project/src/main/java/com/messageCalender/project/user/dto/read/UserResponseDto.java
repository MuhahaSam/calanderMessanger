package com.messageCalender.project.user.dto.read;

import lombok.Data;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<MessengerResponseDto> messengers;
}