package com.lliscano.microservicioquery.service;

import com.lliscano.commons.dto.ResponseDTO;
import com.lliscano.commons.dto.UserDTO;
import com.lliscano.commons.exception.RecordNotFoundException;
import com.lliscano.microservicioquery.mapper.UserMapper;
import com.lliscano.microservicioquery.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository repository;
    private final UserMapper mapper;

    public ResponseDTO<UserDTO> getUserById(Long id) {
        return ResponseDTO.<UserDTO>builder()
                .timestamp(Instant.now())
                .message("User found successfully")
                .data(this.mapper.toDto(
                        this.repository
                                .findById(id)
                                .orElseThrow(() -> new RecordNotFoundException("User not found by given id " + id))))
                .build();
    }

    public ResponseDTO<List<UserDTO>> getAllUsers() {
        return ResponseDTO.<List<UserDTO>>builder()
                .timestamp(Instant.now())
                .message("Users found successfully")
                .data(this.repository.findAll().stream().map(this.mapper::toDto).toList())
                .build();
    }
}
