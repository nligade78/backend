package com.blog.code.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.code.entities.Category;
import com.blog.code.entities.Post;
import com.blog.code.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	

}
