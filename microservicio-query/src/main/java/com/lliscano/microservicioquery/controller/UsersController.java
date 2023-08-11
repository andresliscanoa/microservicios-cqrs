package com.lliscano.microservicioquery.controller;

import com.lliscano.commons.dto.ResponseDTO;
import com.lliscano.microservicioquery.dto.UserDTO;
import com.lliscano.microservicioquery.service.UsersService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService service;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserDTO>>>getAllUsers() {
        return new ResponseEntity<>(this.service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserById(
            @PathVariable(name = "id") @NotNull Long id
    ) {
        return new ResponseEntity<>(this.service.getUserById(id), HttpStatus.OK);
    }
}


