package com.berkhayta.springbootblogrestapi.dto.request;

import com.berkhayta.springbootblogrestapi.entity.Category;
import com.berkhayta.springbootblogrestapi.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PostSaveRequestDto (
        @NotEmpty(message = "Title cannot be empty")
        @Size(max = 30, message = "Title must below 30 character" )
        String title,
        @NotEmpty(message = "Content cannot be empty")
        @Size(max = 200, message = "Content must below 200 character" )
        String content,
        @NotEmpty(message = "Category ID cannot be empty")
        Long categoryId,
        @NotEmpty(message = "User ID cannot be empty")
        Long userId
){
}
