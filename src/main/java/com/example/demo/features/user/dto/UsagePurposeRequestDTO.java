package com.example.demo.features.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UsagePurposeRequestDTO(@NotEmpty(message = "Purpose is required") String purpose) {

}
