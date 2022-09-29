package com.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4,message="title size must be equal to four characters")
	private String  categoryTitle;
	
	@NotBlank
	@Size(min=4,message="description size must be equal to four characters")
	private String  categoryDescription;
	

}
