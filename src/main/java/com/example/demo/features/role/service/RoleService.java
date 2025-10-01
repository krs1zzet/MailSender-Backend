package com.example.demo.features.role.service;



import com.example.demo.features.role.dto.RoleDTO;
import com.example.demo.features.role.dto.request.CreateRoleRequest;
import com.example.demo.features.role.entity.Role;

import java.util.List;

public interface RoleService {
    void save(CreateRoleRequest request);

    void deleteById(Long id);

    List<RoleDTO> findAllReturnRoleDTO();

    RoleDTO findByIdReturnRoleDTO(Long id);

    Role findByRoleNameReturnRole(String name);
}
