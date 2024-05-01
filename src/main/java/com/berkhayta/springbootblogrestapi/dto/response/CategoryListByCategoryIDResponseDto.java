package com.berkhayta.springbootblogrestapi.dto.response;

public record CategoryListByCategoryIDResponseDto (
        Long id,
        String name,
        String description
){
}
