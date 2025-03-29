package edu.eci.cvds.reserves.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.eci.cvds.reserves.dto.UserCreateDto;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toEntity(UserDto userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "Active")
    @Mapping(target = "register", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "authorities", ignore = true)
    User toEntity(UserCreateDto userCreateDto);
}