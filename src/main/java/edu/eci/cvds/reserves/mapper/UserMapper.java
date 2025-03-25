package edu.eci.cvds.reserves.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.eci.cvds.reserves.dto.UserCreateDto;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "Active")
    @Mapping(target = "register", expression = "java(java.time.LocalDate.now())")
    User toEntity(UserCreateDto userCreateDto);
}