package com.example.demo.features.user.service.Impl;


import com.example.demo.features.role.entity.Role;
import com.example.demo.features.role.repository.RoleRepository;
import com.example.demo.features.role.service.RoleService;
import com.example.demo.features.user.dto.UserDTO;

import com.example.demo.features.user.dto.converter.UserDTOConverter;
import com.example.demo.features.user.dto.request.CreateUserRequest;
import com.example.demo.features.user.dto.request.UpdateUserRequest;
import com.example.demo.features.user.entity.User;
import com.example.demo.features.user.repository.UserRepository;
import com.example.demo.features.user.service.UserService;
import com.example.demo.product.exceptions.generic.ApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserDTOConverter userDTOConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void save(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException( "Username already exists : "+request.getUsername());
        }
        Role role = roleService.findByRoleNameReturnRole("USER");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setRole(roleService.findByRoleNameReturnRole(role.getName()));
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateById(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found id : " + id));

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername());
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRoleId() != null) {
            var role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new RuntimeException( "Role not found id=" + request.getRoleId()));
            user.setRole(role);
        }
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException( "User cannot found to delete with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> findAllReturnListUserDTO() {
        return userDTOConverter.convert(userRepository.findAll());
    }

    @Override
    public User findByUsernameReturnUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException( "User not found by username : " + username));
    }

    @Override
    public User findByIdReturnUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException( "User Not Found: " + id));
    }

    @Override
    public UserDTO findByUserIdReturnUserDTO(Long id) {
        return userDTOConverter.convert(
                userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found: " + id)));
    }

    @Override
    public UserDTO findByUsernameReturnUserDTO(String username) {
        return userDTOConverter.convert(
                userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException( "User Not Found: " + username))
        );
    }

    @Override
    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null || "anonymousUser".equals(authentication.getName())) {
            throw new RuntimeException( "No authenticated user");
        }
        var user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException( "User Not Found: " + authentication.getName()));
        return userDTOConverter.convert(user);
    }
    @Override
    public User getCurrentUserReturnUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null || "anonymousUser".equals(authentication.getName())) {
            throw new RuntimeException( "No authenticated user");
        }
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException( "User Not Found: " + authentication.getName()));
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
