package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

@RestController
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<?> createCommentHandler(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		
		CommentDto cd =commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<>(cd,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<?> deletePostByIdHandler(@PathVariable Integer commentId){
		
		commentService.deleteComment(commentId);
		
		return new ResponseEntity<>("post is deleted",HttpStatus.OK);
		
	}
	
	
	

}
