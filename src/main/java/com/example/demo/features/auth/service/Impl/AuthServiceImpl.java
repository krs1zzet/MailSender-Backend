package com.example.demo.features.auth.service.Impl;


import com.example.demo.features.auth.dto.request.signInRequest;
import com.example.demo.features.auth.dto.request.signUpRequest;
import com.example.demo.features.auth.dto.signInDTO;
import com.example.demo.features.auth.dto.signUpDTO;
import com.example.demo.features.auth.service.AuthService;
import com.example.demo.features.auth.service.JwtService;
import com.example.demo.features.role.entity.Role;
import com.example.demo.features.role.service.RoleService;
import com.example.demo.features.user.dto.UserDTO;
import com.example.demo.features.user.dto.request.CreateUserRequest;
import com.example.demo.features.user.entity.User;
import com.example.demo.features.user.service.UserService;
import com.example.demo.product.exceptions.generic.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${auth.default-role:USER}")
    private String defaultRoleName;

    @Value("${security.jwt.expiry-ms:3600000}")
    private long expiryMs;

    @Override
    public signUpDTO signUp(signUpRequest request) {
        if (userService.existByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists: " + request.getUsername());
        }

        Role role = roleService.findByRoleNameReturnRole(defaultRoleName);

        CreateUserRequest createUserReq = new CreateUserRequest();
        createUserReq.setUsername(request.getUsername());
        createUserReq.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(createUserReq);
        UserDTO saved = userService.findByUsernameReturnUserDTO(request.getUsername());

        return signUpDTO.builder()
                .id(saved.getId())
                .user(saved)
                .build();
    }

    @Override
    public signInDTO signIn(signInRequest request) {
        User user = userService.findByUsernameReturnUser(request.getUsername());
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }


        String token = jwtService.generate(user.getUsername());

        return signInDTO.builder()
                .user(userService.findByUsernameReturnUserDTO(request.getUsername()))
                .id(user.getId())
                .token(token)
                .expiresIn(expiryMs)
                .build();

    }
}
