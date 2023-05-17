package com.vietlevan532.javatechnology_final.ddautechstore.Service.Imp;

import com.vietlevan532.javatechnology_final.ddautechstore.DTO.CategoryDTO;
import com.vietlevan532.javatechnology_final.ddautechstore.Models.Category;
import com.vietlevan532.javatechnology_final.ddautechstore.Respository.CategoryRepository;
import com.vietlevan532.javatechnology_final.ddautechstore.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO mapToCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .products(category.getProducts())
                .build();
    }
}
