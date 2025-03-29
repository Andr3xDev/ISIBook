package edu.eci.cvds.reserves.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.eci.cvds.reserves.dto.UserCreateDto;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;

/**
 * Mapper interface for converting between User and UserDto objects.
 * This interface uses MapStruct to generate the implementation at compile time.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "Active")
    @Mapping(target = "register", expression = "java(java.time.LocalDate.now())")
    User toEntity(UserCreateDto userCreateDto);
}