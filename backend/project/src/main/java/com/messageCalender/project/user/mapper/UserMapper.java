package com.messageCalender.project.user.mapper;

import com.messageCalender.project.user.dto.CreateUserDto;
import com.messageCalender.project.user.entities.UserEntity;
import com.messageCalender.project.user.entities.MessengerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {

    @Autowired
    protected MessengerMapper messengerMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "messengers", ignore = true)
    public abstract UserEntity toEntity(CreateUserDto dto);

    @AfterMapping
    protected void afterMapping(CreateUserDto dto, @MappingTarget UserEntity entity) {
        if (dto.getMessengers() != null) {
            dto.getMessengers().forEach(messengerDto -> {
                MessengerEntity messenger = messengerMapper.toEntity(messengerDto);
                entity.addMessenger(messenger);
            });
        }
    }
}