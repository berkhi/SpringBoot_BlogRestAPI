package com.berkhayta.springbootblogrestapi.dto.response;

public record UserListAllResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}

