package com.berkhayta.springbootblogrestapi.mapper;

import com.berkhayta.springbootblogrestapi.dto.request.CategorySaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.CategoryUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-01T17:46:27+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category categorySaveRequestDtoToCategory(CategorySaveRequestDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryDto.name() );
        category.description( categoryDto.description() );

        return category.build();
    }

    @Override
    public CategoryListAllResponseDto categoryToCategoryListDto(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryListAllResponseDto categoryListAllResponseDto = new CategoryListAllResponseDto( id, name, description );

        return categoryListAllResponseDto;
    }

    @Override
    public CategoryListByCategoryIDResponseDto categoryToCategoryDetailDto(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryListByCategoryIDResponseDto categoryListByCategoryIDResponseDto = new CategoryListByCategoryIDResponseDto( id, name, description );

        return categoryListByCategoryIDResponseDto;
    }

    @Override
    public void updateCategoryFromDto(CategoryUpdateRequestDto dto, Category category) {
        if ( dto == null ) {
            return;
        }

        category.setName( dto.name() );
        category.setDescription( dto.description() );
    }
}
