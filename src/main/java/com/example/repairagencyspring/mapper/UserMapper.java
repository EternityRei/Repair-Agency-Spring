package com.example.repairagencyspring.mapper;

import com.example.repairagencyspring.dto.UserDTO;
import com.example.repairagencyspring.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id", ignore = false)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "id", source = "id", ignore = false)
    User userDTOtoUser(UserDTO userDTO);

    List<UserDTO> toUserDTO(List<User> allUsers);
}
