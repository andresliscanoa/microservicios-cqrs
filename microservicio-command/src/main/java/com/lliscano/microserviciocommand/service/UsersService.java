package com.lliscano.microserviciocommand.service;

import com.lliscano.commons.dto.KafkaEventDTO;
import com.lliscano.commons.dto.ResponseDTO;;
import com.lliscano.microserviciocommand.dto.UserDTO;
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
                        .data(user)
                        .build()
        );
        return ResponseDTO.<UserDTO>builder()
                .timestamp(Instant.now())
                .data(userDTO)
                .message("User created successfully")
                .build();
    }
}
