package com.berkhayta.springbootblogrestapi.dto.response;

public record PostListByUserIDResponseDto(
        Long id,
        String title,
        String content,
        Long categoryId,
        Long userId
) {
}
