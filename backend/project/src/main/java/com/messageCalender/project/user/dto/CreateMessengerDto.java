package com.messageCalender.project.user.dto;

import com.messageCalender.project.user.entities.MessengerTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessengerDto {

    @NotEmpty(message = "Messenger nickname cannot be empty")
    private String messengerNick;

    @NotNull(message = "Messenger type cannot be null")
    private MessengerTypeEnum messengerType;
}