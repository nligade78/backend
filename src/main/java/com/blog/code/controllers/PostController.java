package com.blog.code.controllers;

import com.blog.code.payloads.ApiResponse;
import com.blog.code.payloads.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.code.payloads.PostDto;
import com.blog.code.services.PostService;

import java.util.List;

import static com.blog.code.constants.AppConstants.*;

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
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=SORT_DIR,required=false) String sortDir
	)
	{
		PostResponse postResponse= this.postService.getAllPost(pageNumber - 1, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
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

	//search
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
	{
		List<PostDto> results= this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
	}



}
