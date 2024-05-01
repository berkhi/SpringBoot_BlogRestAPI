package com.berkhayta.springbootblogrestapi.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CategorySaveRequestDto(
        @NotEmpty(message = "Name cannot be empty")
        String name,
        @NotEmpty(message = "Description cannot be empty")
        String description
) {
}
