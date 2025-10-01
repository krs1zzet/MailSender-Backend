package com.example.demo.features.user.service;




import com.example.demo.features.user.dto.UserDTO;
import com.example.demo.features.user.dto.request.CreateUserRequest;
import com.example.demo.features.user.dto.request.UpdateUserRequest;
import com.example.demo.features.user.entity.User;

import java.util.List;

public interface UserService {

    void save(CreateUserRequest request);

    void updateById(Long id, UpdateUserRequest request);

    void deleteById(Long id);

    List<UserDTO> findAllReturnListUserDTO();

    User findByUsernameReturnUser(String username);
    User findByIdReturnUser(Long id);

    UserDTO findByUserIdReturnUserDTO(Long id);

    UserDTO findByUsernameReturnUserDTO(String username);

    UserDTO getCurrentUser();
    User getCurrentUserReturnUser();

    boolean existByUsername(String username);
}
