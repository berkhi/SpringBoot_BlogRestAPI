package com.berkhayta.springbootblogrestapi.dto.response;

public record CategoryListAllResponseDto(
        Long id,
        String name,
        String description
) {
}
