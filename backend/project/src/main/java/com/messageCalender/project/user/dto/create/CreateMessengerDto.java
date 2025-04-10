package com.messageCalender.project.user.dto.create;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessengerDto {

    @NotEmpty(message = "Messenger nick cannot be empty")
    private String messengerNick;

    @NotEmpty(message = "Messenger type cannot be empty")
    private String messengerType;
}