package com.example.demo.features.role.service.Impl;


import com.example.demo.features.role.dto.RoleDTO;
import com.example.demo.features.role.dto.converter.RoleDTOConverter;
import com.example.demo.features.role.dto.request.CreateRoleRequest;
import com.example.demo.features.role.entity.Role;
import com.example.demo.features.role.repository.RoleRepository;
import com.example.demo.features.role.service.RoleService;
import com.example.demo.product.exceptions.generic.ApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleDTOConverter roleDTOConverter;


    @Override
    @Transactional
    public void save(CreateRoleRequest request) {
        Role role = new Role();
        if (roleRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException( "Role already exists: " + request.getName());
        }
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        Role theRole = role.orElseThrow(()-> new RuntimeException("Role not found with id = " + id));
        roleRepository.delete(theRole);
    }

    @Override
    public List<RoleDTO> findAllReturnRoleDTO() {
        return roleDTOConverter.convert(roleRepository.findAll());
    }

    @Override
    public RoleDTO findByIdReturnRoleDTO(Long id) {
        return roleDTOConverter.convert(
                roleRepository.findById(id)
                        .orElseThrow(
                                ()-> new RuntimeException("Role not found with id = " + id)));
    }



    @Override
    public Role findByRoleNameReturnRole(String name) {
        return roleRepository.findByName(name).orElseThrow(()-> new RuntimeException("Role not found with name = " + name));
    }
}
