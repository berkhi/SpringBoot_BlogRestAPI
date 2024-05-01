package com.berkhayta.springbootblogrestapi.dto.response;

public record PostListAllResponseDto (
        Long id,
        String title,
        String content,
        Long categoryId,
        Long userId
) {
}
