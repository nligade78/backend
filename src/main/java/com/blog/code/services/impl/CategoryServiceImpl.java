package com.blog.code.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.code.entities.Category;
import com.blog.code.exceptions.ResourceNotFoundException;
import com.blog.code.payloads.CategoryDto;
import com.blog.code.repositories.CategoryRepo;
import com.blog.code.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category =this.dtoTOCategory(categoryDto);
		Category savedCategory=this.categoryRepo.save(category);
		return this.CategoryTODto(savedCategory);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepo.save(category);
		CategoryDto categoryDto1=this.CategoryTODto(updatedCategory);
		
		return categoryDto1;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category deletedCategory = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		this.categoryRepo.delete(deletedCategory);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
		return this.CategoryTODto(category);
		
	}

	@Override
	public List<CategoryDto> getCategorys() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map(user->this.CategoryTODto(user)).collect(Collectors.toList());
		return categoryDtos;
	}
	
	public Category dtoTOCategory(CategoryDto categoryDto)
	{
		Category category=this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	public CategoryDto CategoryTODto(Category category)
	{
		CategoryDto categoryDto=this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
