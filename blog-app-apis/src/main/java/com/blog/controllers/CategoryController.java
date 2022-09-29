package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/categories")
	public ResponseEntity<?> saveCategoryHandler(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto cd = categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<>(cd,HttpStatus.CREATED);
		 
		 
	}
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<?> updateCategoryHandler(@Valid @PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto){
		
		CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
		
		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/categories/{categoryId}")
	public String deleteCategoryHandler(@PathVariable Integer categoryId){
		
		categoryService.deleteCategorybyId(categoryId);
		
		return "deleted successfully";
		
		
		
		
		
	}
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<?> getCategoryByIdHandler(@PathVariable Integer categoryId){
		
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
		
		return new ResponseEntity<>(categoryDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategoriesHandler(){
		
		List<CategoryDto> categoryDtos = categoryService.getAllCategories();
		
		return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
		
	}
	
	
	
	

}
