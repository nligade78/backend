package com.blog.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.code.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
