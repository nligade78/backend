package com.blog.code.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private Integer Id;

	@NotEmpty(message="User name must not be empty !!")
	@Size(min=4,message="User Name Must be min of 4 characters !!")
	private String name;

	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password Must be min of 3 characters and max of 10 characters !!")
	private String password;
	
	@NotEmpty
	private String about; 

}
