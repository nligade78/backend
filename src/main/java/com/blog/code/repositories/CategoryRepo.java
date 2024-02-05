package com.blog.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.code.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
