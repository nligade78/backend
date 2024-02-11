package com.blog.code.controllers;

import com.blog.code.entities.Post;
import com.blog.code.payloads.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.code.payloads.PostDto;
import com.blog.code.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	//create
	@PostMapping("/postCreated/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}

	//get by category
	@GetMapping("/category/{categoryID}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryID)
	{
		List<PostDto> posts= this.postService.getPostsByCategory(categoryID);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

	}



	//get by user
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts= this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

	}


	//getAllPost

	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostDto>> getAllPost(
			@RequestParam(value="pageNumber",defaultValue="1",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="5",required=false)Integer pageSize
	)
	{
		List<PostDto> allPost= this.postService.getAllPost(pageNumber-1,pageSize);
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}

	//getPostUsingID
	@GetMapping("/postById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}

	//delete-post

	@DeleteMapping("/deletePost/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
	 this.postService.deletePost(postId);
	 return new ApiResponse("Post deleted successfully !!",true);
	}

	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto updatePost= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}



}
