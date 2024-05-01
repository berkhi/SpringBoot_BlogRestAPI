package com.berkhayta.springbootblogrestapi.dto.response;

public record UserListByIdResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email) {
}
