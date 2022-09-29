package com.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryDao;
import com.blog.repositories.PostDao;
import com.blog.repositories.UserDao;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User does not exist with id"+userId));
		
		Category category = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category does not exist with id"+categoryId));

		
		Post post = modelMapper.map(postDto, Post.class);
		
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		
		Post newPost = postDao.save(post);
		
		
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("post does not exist with postid"+postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postDao.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePostById(Integer postId) {
		Post post = postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("post does not exist with postid"+postId));
		
		postDao.delete(post);
		
		
	}

	@Override
	public List<PostDto> getAllPosts() {
		
		List<Post> posts = postDao.findAll();
		
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user does not exist with id"+userId));
		
		
		List<Post> posts = postDao.findByUser(user);
		
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        
        return postDtos;

	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category category = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category does not exist with id"+categoryId));
		
		List<Post> posts = postDao.findByCategory(category);
		
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("post does not exist with postid"+postId));
		
		return modelMapper.map(post, PostDto.class);
		
	}

}
