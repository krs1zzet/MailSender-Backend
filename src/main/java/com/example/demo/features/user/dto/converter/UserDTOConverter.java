package com.example.demo.features.user.dto.converter;

import com.example.demo.features.user.dto.UserDTO;
import com.example.demo.features.user.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDTOConverter {
    public UserDTO convert(User from) {
        return new UserDTO(
                from.getId(),
                from.getUsername(),
                from.getRole().getId(),
                from.getCreatedAt()
        );
    }

    public List<UserDTO> convert(List<User> from) {
        return from.stream().map(this::convert).toList();

    }
}


