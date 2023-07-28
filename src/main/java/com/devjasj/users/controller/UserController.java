package com.devjasj.users.controller;

import com.devjasj.users.domain.dto.UserDTO;
import com.devjasj.users.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("api/users")
public class UserController {

    public final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid @NotNull UserDTO userDTO) {
        UserDTO userSaved = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable @Valid @Positive Long id) {
        UserDTO userFound = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userFound);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> allUsers = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable @Valid @Positive Long id, @RequestBody @Valid @NotNull UserDTO userDTO) {
        UserDTO userEdited = userService.updateUserById(id, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userEdited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable @Valid @Positive Long id) {
        userService.deleteUserById(id);

        String message = String.format("O usuário com id %d foi excluído com sucesso.", id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }
}
