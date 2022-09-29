package com.blog.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.PostDto;
import com.blog.services.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<?> createPostHandler(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto createdPostDto = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<>(createdPostDto,HttpStatus.CREATED);
	}
	@GetMapping("/User/{userId}/posts")
	public ResponseEntity<?> getPostByUserHandler(@PathVariable Integer userId){
		
		List<PostDto> postDtos = postService.getPostByUser(userId);
		
		return new ResponseEntity<>(postDtos,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/Category/{categoryId}/posts")
	public ResponseEntity<?> getPostByCategoryHandler(@PathVariable Integer categoryId){
		
		List<PostDto> postDtos = postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<>(postDtos,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<?> getPostByIdHandler(@PathVariable Integer postId){
		
		PostDto postDto = postService.getPostById(postId);
		
		return new ResponseEntity<>(postDto,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<?> getAllPostsHandler(){
		
		List<PostDto> postDtos = postService.getAllPosts();
		
		return new ResponseEntity<>(postDtos,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePostByIdHandler(@PathVariable Integer postId){
		
		postService.deletePostById(postId);
		
		return new ResponseEntity<>("post is deleted",HttpStatus.OK);
		
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<?> updatePostHandler(@RequestBody PostDto postDto,@PathVariable Integer postId){
		
		PostDto updatedPostDto = postService.updatePost(postDto, postId);
		
		return new ResponseEntity<>(updatedPostDto,HttpStatus.CREATED);
	}
	
	

}
