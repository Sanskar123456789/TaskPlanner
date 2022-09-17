package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Models.User;

public interface repo extends MongoRepository<User, Integer>{
	 @Query("{id :?0}")
	 User findbyid(int id);
}
