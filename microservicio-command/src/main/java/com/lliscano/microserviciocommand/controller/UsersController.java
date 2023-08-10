package com.lliscano.microserviciocommand.controller;


import com.lliscano.commons.dto.ResponseDTO;
import com.lliscano.microserviciocommand.dto.UserDTO;
import com.lliscano.microserviciocommand.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService service;

    @PostMapping
    public ResponseEntity<ResponseDTO<UserDTO>> createUser(
            @RequestBody @Valid UserDTO userDTO
    ) {
        return new ResponseEntity<>(this.service.saveUser(userDTO),HttpStatus.CREATED);
    }
}
