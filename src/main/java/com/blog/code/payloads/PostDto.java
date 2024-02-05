package com.blog.code.payloads;

import java.util.Date;

import com.blog.code.entities.Category;
import com.blog.code.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private String title;
	private String Content;
	private String imageName;
	private Date addDate;
	private CategoryDto category;
	private UserDto user;

	
	//private String imageName="default.name";
	
	

}
