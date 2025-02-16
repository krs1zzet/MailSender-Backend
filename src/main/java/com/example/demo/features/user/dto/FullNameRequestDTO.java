package com.example.demo.features.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record FullNameRequestDTO(@NotEmpty(message = "Full name is required") String fullName) {

}
