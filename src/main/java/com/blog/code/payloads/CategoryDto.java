package com.blog.code.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	

	private Integer categoryId;
	@NotEmpty
	@Size(min=3,max=10,message="Password Must be min of 3 characters and max of 10 characters !!")
	private String categoryTitle;
	@NotEmpty
	@Size(min=3,max=10,message="Password Must be min of 3 characters and max of 10 characters !!")
	private String categoryDescription;

}
