package com.ecommerce.EcommerceBackend.service;

import com.ecommerce.EcommerceBackend.Dto.CategoryDto;
import com.ecommerce.EcommerceBackend.model.Category;
import com.ecommerce.EcommerceBackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(CategoryDto categoryDto){
        Category category = toCategory(categoryDto);
        categoryRepository.save(category);
    }

    private Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }

    public List<CategoryDto> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> toCategoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    private CategoryDto toCategoryDto(Category category) {
        return  new CategoryDto(category.getCategoryId(), category.getCategoryName());
    }

    public CategoryDto getCategoryDto(int id){
       Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
       return toCategoryDto(category);
    }

    public void updateCategory(Integer id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
}
