package com.blog.code.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.blog.code.payloads.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.code.entities.Category;
import com.blog.code.entities.Post;
import com.blog.code.entities.User;
import com.blog.code.exceptions.ResourceNotFoundException;
import com.blog.code.payloads.PostDto;
import com.blog.code.repositories.CategoryRepo;
import com.blog.code.repositories.PostRepo;
import com.blog.code.repositories.UserRepo;
import com.blog.code.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
	
		
		return this.modelMapper.map(newPost, PostDto.class );
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost= this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID", postId));
		this.postRepo.delete(post);


	}

	@Override
	public List<PostDto> getAllPost() {

		List<Post> allPost= postRepo.findAll();
		List<PostDto> allPostDtos= allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post= this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user= userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts= this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
