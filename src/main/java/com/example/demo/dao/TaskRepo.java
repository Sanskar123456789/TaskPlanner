package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Models.Task;

public interface TaskRepo extends MongoRepository<Task, Integer>{
	
	@Query("{id :?0}")
	Task findbyid(int id);
	
	@Query("{title :?0}")
	Task findbytitle(String title);
}
