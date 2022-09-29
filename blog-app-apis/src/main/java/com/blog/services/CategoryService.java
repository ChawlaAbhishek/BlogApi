package com.blog.services;

import java.util.List;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto) throws ResourceNotFoundException;
	void deleteCategorybyId(Integer categoryId) throws ResourceNotFoundException;
	CategoryDto getCategoryById(Integer categoryId) throws ResourceNotFoundException;
	List<CategoryDto> getAllCategories();
	

}
