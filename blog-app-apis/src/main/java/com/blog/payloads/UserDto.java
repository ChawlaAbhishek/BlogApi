package com.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	@NotNull
	@Size(min=4,message="user name must be min of 4 characters")
	private String name;
	@Email(message="enter a valid email address")
	private String email;
	@NotNull
	@Size(min=4,max=10,message="password length should be between 4 to 10 char")
	private String password;
	@NotNull
	private String about;

}
