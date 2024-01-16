package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.Dto.CategoryDto;
import com.ecommerce.EcommerceBackend.model.Category;
import com.ecommerce.EcommerceBackend.service.CategoryService;
import com.ecommerce.EcommerceBackend.view.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategroy(@RequestBody CategoryDto category){
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Integer id){
        return  categoryService.getCategoryDto(id);
    }

    @GetMapping
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDto category,@PathVariable Integer id){

        try{
            categoryService.updateCategory(id,category);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id){
        try{
            categoryService.deleteCategory(id);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true),HttpStatus.OK);
    }
}
