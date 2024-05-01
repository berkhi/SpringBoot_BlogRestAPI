package com.berkhayta.springbootblogrestapi.controller;

import com.berkhayta.springbootblogrestapi.dto.request.CategorySaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.CategoryUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.CategoryListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Category;
import com.berkhayta.springbootblogrestapi.mapper.CategoryMapper;
import com.berkhayta.springbootblogrestapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.berkhayta.springbootblogrestapi.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT + CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Category> saveDto(CategorySaveRequestDto dto) {
        return ResponseEntity.ok(categoryService.saveCategoryDto(dto));
    }

    //veritabanındaki tüm kategorileri CategoryListAllResponseDto listesi olarak döndürür.
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryListAllResponseDto>> getAllCategories() {
        List<CategoryListAllResponseDto> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    //categoryId ile ilişkili kategorinin detaylarını CategoryListByCategoryIDResponseDto olarak döndürür.
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryListByCategoryIDResponseDto> getCategoryById(@PathVariable Long categoryId) {
        CategoryListByCategoryIDResponseDto category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    //categoryId ile ilişkili kategoriyi CategoryUpdateRequestDto kullanarak günceller. Güncellenmiş kategori bilgilerini CategoryListByCategoryIDResponseDto olarak döndürür.
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryListByCategoryIDResponseDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto dto) {
        Category updatedCategory = categoryService.updateCategory(categoryId, dto);
        CategoryListByCategoryIDResponseDto categoryDto = CategoryMapper.INSTANCE.categoryToCategoryDetailDto(updatedCategory);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok().build();  // Başarılı silme işlemi
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // Kategori bulunamadı
        }
    }

    // -------------- LISTING AND FILTERING ---------------------

    //Kategorileri isme göre arama seçeneği (name parametresi ile).
    @GetMapping("/name")
    public ResponseEntity<List<CategoryListAllResponseDto>> getCategoriesByName(@RequestParam String name) {
        List<CategoryListAllResponseDto> categories = categoryService.findCategoriesByName(name);
        return ResponseEntity.ok(categories);
    }
}
