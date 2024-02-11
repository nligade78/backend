package com.blog.code.services;

import com.blog.code.payloads.PostDto;
import com.blog.code.payloads.PostResponse;

import java.util.List;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);


	//get Single Post
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search Post
	List<PostDto> searchPosts(String keyword);
	
	
	

}
