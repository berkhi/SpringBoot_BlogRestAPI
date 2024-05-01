package com.berkhayta.springbootblogrestapi.dto.response;

import java.util.Objects;

public record UpdateUserListByIdResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password
) {
    public UpdateUserListByIdResponseDto {
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(password, "password must not be null");
    }
}
