package com.lliscano.microserviciocommand.controller;


import com.lliscano.commons.dto.ResponseDTO;
import com.lliscano.commons.dto.UserDTO;
import com.lliscano.microserviciocommand.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(
            @RequestBody @Valid UserDTO userDTO
    ) {
        return new ResponseEntity<>(this.service.updateUser(userDTO),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteUser(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(this.service.deleteUser(id),HttpStatus.OK);
    }
}
