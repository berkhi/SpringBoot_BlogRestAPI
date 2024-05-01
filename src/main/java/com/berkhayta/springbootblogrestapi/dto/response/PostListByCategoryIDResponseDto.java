package com.berkhayta.springbootblogrestapi.dto.response;

public record PostListByCategoryIDResponseDto(
        Long id,
        String title,
        String content,
        Long categoryId,
        Long userId
) {
}
