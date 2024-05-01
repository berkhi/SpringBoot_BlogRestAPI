package com.berkhayta.springbootblogrestapi.repository;

import com.berkhayta.springbootblogrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingIgnoreCase(String name);

}
