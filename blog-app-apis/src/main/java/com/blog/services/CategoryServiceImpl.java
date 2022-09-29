package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper modelMapper;
	


	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = modelMapper.map(categoryDto, Category.class);
		
		categoryDao.save(category);
		
		return modelMapper.map(category, CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) throws ResourceNotFoundException {
		
		Category category = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category does not exist with id"+categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = categoryDao.save(category);
		
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategorybyId(Integer categoryId) throws ResourceNotFoundException {
		
		Category category = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category does not exist with id"+categoryId));
		categoryDao.delete(category);
		
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) throws ResourceNotFoundException {
		
		Category category = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category does not exist with id"+categoryId));

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = categoryDao.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map((cat)->modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDtos;
	}

}
