package com.blog.code.services;

import com.blog.code.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//delete
	public void deleteCategory(Integer categoryId);
	//get
	public CategoryDto getCategory(Integer categoryId);
	//getAll
	List<CategoryDto> getCategorys();
	
}
