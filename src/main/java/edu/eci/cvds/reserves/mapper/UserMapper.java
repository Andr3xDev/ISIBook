package edu.eci.cvds.reserves.mapper;

import org.mapstruct.Mapper;

import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDTO);
}