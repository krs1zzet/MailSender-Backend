package com.example.demo.features.user.controller;


import com.example.demo.features.user.dto.UserDTO;
import com.example.demo.features.user.dto.request.CreateUserRequest;
import com.example.demo.features.user.dto.request.UpdateUserRequest;
import com.example.demo.features.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUserRequest request) {
        log.info("Role created with name : {}", request.getUsername());
        userService.save(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.findAllReturnListUserDTO());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        log.info("Get user with id : {}", id);
        return ResponseEntity.ok(userService.findByUserIdReturnUserDTO(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete user with id : {}", id);
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        log.info("Update user with id : {}", id);
        userService.updateById(id, request );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

}
