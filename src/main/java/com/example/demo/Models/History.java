package com.example.demo.Models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("History")
public class History {
	@Id
	private long id;
	private Date CreatedAt;
	private long Taskid;
	private List<TaskHistoryModel> TaskHistory;
	private Date LastUpdated;
}
