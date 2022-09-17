package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Models.History;

public interface HistoryRepo extends MongoRepository<History, Integer> {
	@Query("{Taskid :?0}")
	History findbyTaskid(long id);
}
