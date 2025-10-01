package com.example.demo.features.role.controller;

import com.example.demo.features.role.dto.RoleDTO;
import com.example.demo.features.role.dto.request.CreateRoleRequest;
import com.example.demo.features.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateRoleRequest request) {
        roleService.save(request);
        log.info("Role created with name : {}", request.getName());
        return  ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        log.info("Get all roles");
        return ResponseEntity.ok(roleService.findAllReturnRoleDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable Long id) {
        log.info("Role getById with id : {}", id);
        return ResponseEntity.ok(roleService.findByIdReturnRoleDTO(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.deleteById(id);
        log.info("Role deleted with id : {}", id);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CreateRoleRequest request) {
        roleService.save(request);
        log.info("Role updated with name : {}", request.getName());
        return ResponseEntity.ok().build();
    }
}
