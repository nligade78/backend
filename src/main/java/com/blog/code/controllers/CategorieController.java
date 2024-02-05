package com.blog.code.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.code.payloads.ApiResponse;
import com.blog.code.payloads.CategoryDto;
import com.blog.code.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categorie")
public class CategorieController {
	
	@Autowired
	private CategoryService categoryService;
	
	//post - categories
	
	@PostMapping("/CreateNewCategory")
	public ResponseEntity<CategoryDto> createCategorie(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategoryDto= this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
	}
	
	
	//update - categories
	
	@PutMapping("/categoryUpdate/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategor(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")Integer categoryId)
	{
		CategoryDto updatecategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
		return  ResponseEntity.ok(updatecategoryDto);
		
	}
	
	
	//delete - categories
	
	@DeleteMapping("/categoryDelete/{categoryId}")
	public ResponseEntity<?> deleteCategor(@PathVariable("categoryId")Integer categoryId)
	{
		 this.categoryService.deleteCategory(categoryId);
		 return new ResponseEntity<Object>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
	
	//get - categories
	@GetMapping("/getCategoryById/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleUser(@PathVariable Integer categoryId)
	{
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
		
	}
	
	
	//getAll - categories
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategorys()
	{
		return ResponseEntity.ok(this.categoryService.getCategorys());
	}
	

}
