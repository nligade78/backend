package com.blog.code.services;

import com.blog.code.entities.Post;
import com.blog.code.payloads.PostDto;
import java.util.List;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	Post updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
	List<Post> getAllPost();
	
	//get Single Post
	Post getPostById(Integer postId);
	
	//get all post by category
	List<Post> getPostsByCategory(Integer categoryId);
	
	//get all post by user
	List<Post> getPostsByUser(Integer userId);
	
	//search Post
	List<Post> searchPosts(String keyword);
	
	
	

}
