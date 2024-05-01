package com.berkhayta.springbootblogrestapi.mapper;

import com.berkhayta.springbootblogrestapi.dto.request.CategorySaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.CategoryUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(CategoryMapper.class);
    Category categorySaveRequestDtoToCategory(CategorySaveRequestDto categoryDto);
    CategoryListAllResponseDto categoryToCategoryListDto(Category category);
    CategoryListByCategoryIDResponseDto categoryToCategoryDetailDto(Category category);
    void updateCategoryFromDto(CategoryUpdateRequestDto dto, @MappingTarget Category category);

}
