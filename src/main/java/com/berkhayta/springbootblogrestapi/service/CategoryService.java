package com.berkhayta.springbootblogrestapi.service;

import com.berkhayta.springbootblogrestapi.dto.request.CategorySaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.CategoryUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Category;
import com.berkhayta.springbootblogrestapi.exceptions.BlogAppException;
import com.berkhayta.springbootblogrestapi.exceptions.ErrorType;
import com.berkhayta.springbootblogrestapi.mapper.CategoryMapper;
import com.berkhayta.springbootblogrestapi.repository.CategoryRepository;
import com.berkhayta.springbootblogrestapi.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService extends ServiceManager<Category, Long> {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategoryDto(CategorySaveRequestDto dto){
        return categoryRepository.save(CategoryMapper.INSTANCE.categorySaveRequestDtoToCategory(dto));
    }

    //Category nesnesini CategoryMapper kullanarak CategoryListDto'ya dönüştürüyoruz.
    public List<CategoryListAllResponseDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryListDto)
                .collect(Collectors.toList());
    }

    //CategoryService sınıfında, belirli bir kategoriye ait detayları CategoryListByCategoryIDResponseDto olarak döndürüren metod
    public CategoryListByCategoryIDResponseDto findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryMapper.INSTANCE::categoryToCategoryDetailDto)
                .orElseThrow(() -> new BlogAppException(ErrorType.CATEGORY_NOT_FOUND, categoryId + " id li kategori bulunamadı."));
    }

    public Category updateCategory(Long categoryId, CategoryUpdateRequestDto dto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BlogAppException(ErrorType.CATEGORY_NOT_FOUND, categoryId + " id li kategori bulunamadı."));

        CategoryMapper.INSTANCE.updateCategoryFromDto(dto, category);
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryListAllResponseDto> findCategoriesByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name).stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryListDto)
                .collect(Collectors.toList());
    }
}
