package com.example.demo.features.role.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateRoleRequest extends BaseRoleRequest {
    private String name;
    private String description;
}
