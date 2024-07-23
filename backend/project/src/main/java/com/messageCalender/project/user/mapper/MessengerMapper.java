package com.messageCalender.project.user.mapper;

import com.messageCalender.project.user.dto.CreateMessengerDto;
import com.messageCalender.project.user.entities.MessengerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessengerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    MessengerEntity toEntity(CreateMessengerDto dto);

    List<MessengerEntity> toEntityList(List<CreateMessengerDto> dtos);
}