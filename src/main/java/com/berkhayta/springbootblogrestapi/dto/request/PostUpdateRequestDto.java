package com.berkhayta.springbootblogrestapi.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record PostUpdateRequestDto(
        @NotEmpty(message = "Title cannot be empty")
        String title,
        @NotEmpty(message = "Content cannot be empty")
        String content,
        @NotEmpty(message = "Category ID cannot be empty")
        Long categoryId) {
}
