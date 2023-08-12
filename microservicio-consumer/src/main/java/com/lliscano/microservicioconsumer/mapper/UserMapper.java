package com.lliscano.microservicioconsumer.mapper;


import com.lliscano.commons.dto.UserDTO;
import com.lliscano.microservicioconsumer.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(Users user);
    Users toEntity(UserDTO userDTO);
}
