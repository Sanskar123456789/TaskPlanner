package com.example.demo.Models;
import java.util.Date;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Task")
public class Task {
	@Id
	private long id;
	private String title; 		// - title of the task
	private String description; // - The basic description of task
	private User createdBy; 	// - Who created the task
	private User assignedTo; 	// - Who is assigned to the task
	private Date completedOn; // - Date when task was completed
	private String status="CREATED"; 		// - the status of task 

}
