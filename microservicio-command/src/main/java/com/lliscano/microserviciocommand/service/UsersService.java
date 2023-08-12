package com.lliscano.microserviciocommand.service;

import com.lliscano.commons.dto.KafkaEventDTO;
import com.lliscano.commons.dto.ResponseDTO;
import com.lliscano.commons.dto.UserDTO;
import com.lliscano.commons.exception.RecordNotFoundException;
import com.lliscano.microserviciocommand.entity.Users;
import com.lliscano.microserviciocommand.mapper.UserMapper;
import com.lliscano.microserviciocommand.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class UsersService {

    private static final String TOPIC = "user-event-topic";
    private final UsersRepository repository;
    private final UserMapper mapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ResponseDTO<UserDTO> saveUser(UserDTO userDTO) {
        Users user = this.repository.save(this.mapper.toEntity(userDTO));
        this.kafkaTemplate.send(
                TOPIC, KafkaEventDTO.builder()
                        .event("POST")
                        .data(userDTO)
                        .build()
        );
        return ResponseDTO.<UserDTO>builder()
                .timestamp(Instant.now())
                .data(this.mapper.toDto(user))
                .message("User created successfully")
                .build();
    }

    public ResponseDTO<UserDTO> updateUser(UserDTO userDTO) {
        this.repository.findById(userDTO.getId())
                .orElseThrow(() -> new RecordNotFoundException("No existe el usuario con id: "+userDTO.getId()));
        this.repository.save(this.mapper.toEntity(userDTO));
        this.kafkaTemplate.send(
                TOPIC, KafkaEventDTO.builder()
                        .event("PUT")
                        .data(userDTO)
                        .build()
        );
        return ResponseDTO.<UserDTO>builder()
                .timestamp(Instant.now())
                .data(userDTO)
                .message("User created successfully")
                .build();
    }
    public ResponseDTO<String> deleteUser(Long id) {
        Users user = this.repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe el usuario con id: "+id));
        this.repository.deleteById(id);
        this.kafkaTemplate.send(
                TOPIC, KafkaEventDTO.builder()
                        .event("DELETE")
                        .data(this.mapper.toDto(user))
                        .build()
        );
        return ResponseDTO.<String>builder()
                .timestamp(Instant.now())
                .message("User deleted successfully")
                .build();
    }
}
