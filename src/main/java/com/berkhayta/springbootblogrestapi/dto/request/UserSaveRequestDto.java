package com.berkhayta.springbootblogrestapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserSaveRequestDto(
        String firstName,
        String lastName,
        @NotEmpty(message = "Email cannot be empty")
        String email,
        @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
        String password
) {
}
