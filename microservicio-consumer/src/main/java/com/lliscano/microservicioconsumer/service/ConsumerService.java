package com.lliscano.microservicioconsumer.service;

import com.lliscano.commons.dto.KafkaEventDTO;
import com.lliscano.microservicioconsumer.mapper.UserMapper;
import com.lliscano.microservicioconsumer.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ConsumerService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    public void syncUsers(KafkaEventDTO kafkaEventDTO) {
        log.info("KafkaEventDTO: {}",kafkaEventDTO);
        if(kafkaEventDTO.getEvent().equalsIgnoreCase("POST"))
            this.usersRepository.save(this.userMapper.toEntity(kafkaEventDTO.getData()));
        if(kafkaEventDTO.getEvent().equalsIgnoreCase("PUT"))
            this.usersRepository.save(this.userMapper.toEntity(kafkaEventDTO.getData()));
        if(kafkaEventDTO.getEvent().equalsIgnoreCase("DELETE"))
            this.usersRepository.deleteById(kafkaEventDTO.getData().getId());
    }
}
