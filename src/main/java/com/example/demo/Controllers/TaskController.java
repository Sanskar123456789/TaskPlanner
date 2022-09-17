package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.History;
import com.example.demo.Models.Task;
import com.example.demo.Models.TaskHistoryModel;
import com.example.demo.dao.HDAO;
import com.example.demo.dao.HistoryRepo;
import com.example.demo.dao.TaskRepo;
import com.example.demo.dao.repo;

import lombok.Data;
@Data
class task {
	String title; 		// - title of the task
	String description; // - The basic description of task
	Integer createdBy; 	// - Who created the task
	Integer assignedTo; 	// - Who is assigned to the task
}

@RestController
public class TaskController {
	
	@Autowired TaskRepo obj;
	@Autowired repo objs;
	@Autowired HistoryRepo Hrepo;
	@Autowired HDAO hdao;
	
	@PostMapping("/NewTask")
	public Task NewTask(@RequestBody task e) {
		Task t = new Task();
		t.setTitle(e.getTitle());
		t.setDescription(e.getDescription());
		t.setAssignedTo(objs.findbyid(e.getAssignedTo()));
		t.setCreatedBy(objs.findbyid(e.getCreatedBy()));
		t.setId(obj.count()+1);
		
		History history = new History();
		history.setId(Hrepo.count());
		Date d = new Date();
		history.setCreatedAt(d);
		history.setLastUpdated(d);
		
		TaskHistoryModel tm = new TaskHistoryModel();
		tm.setReason("Task Created");
		tm.setTask(t);
		
		List<TaskHistoryModel> thm = new ArrayList<>();
		thm.add(tm);
		
		history.setTaskHistory(thm);
		t = obj.save(t);
		history.setTaskid(t.getId());
		Hrepo.save(history);
		
		return t;
	}
	
	@GetMapping("/allTask")
	public List<Task> allTask(){
		List<Task> t  = obj.findAll();
		System.out.println(t);
		return t;
	}
	
	@SuppressWarnings("finally")
	@PutMapping("/updateTask/{id}")
	public Task updateTask(@RequestBody task e,@PathVariable("id") int id) {
		Task t = obj.findbyid(id);
		
		if(e.getTitle()!=null) {			
			t.setTitle(e.getTitle());
		}
		
		if(e.getDescription()!=null) {			
			t.setDescription(e.getDescription());
		}
		
		
		if(e.getAssignedTo()!=null) {			
			t.setAssignedTo(objs.findbyid(e.getAssignedTo()));
		}
		
		if(e.getCreatedBy()!=null) {			
			t.setCreatedBy(objs.findbyid(e.getCreatedBy()));
		}
		
		History h = Hrepo.findbyTaskid(t.getId());
		List<TaskHistoryModel> tasks = h.getTaskHistory();
		TaskHistoryModel tm = new TaskHistoryModel();
		tm.setReason("Task Info Changed");
		tm.setTask(t);
		tasks.add(tm);
		h.setTaskHistory(tasks);
		Date d = new Date();
		h.setLastUpdated(d);
		System.out.println("History ---> "+h + "\n Task --> " + t);
		try {			
			hdao.update(h);
		}catch(Exception exception) {
			System.out.println(exception);
		}finally {			
			return obj.save(t);
		}
	}
	
	@DeleteMapping("/deleteTask/{id}")
	public Boolean deleteTask(@PathVariable("id") int id) {
		obj.deleteById(id);
		return true;
	}
	
	@SuppressWarnings("finally")
	@PutMapping("/TaskCompleted/{id}")
	public Task completeTask(@PathVariable("id") int id) {
		System.out.println("id ---> "+ id);
		Task t =obj.findbyid(id);
		System.out.println(t);
		Date d = new Date();
		t.setCompletedOn(d);
		t.setStatus("DONE");
		
		History h = Hrepo.findbyTaskid(t.getId());
		List<TaskHistoryModel> tasks = h.getTaskHistory();
		TaskHistoryModel tm = new TaskHistoryModel();
		tm.setReason("Task Completed");
		tm.setTask(t);
		tasks.add(tm);
		h.setTaskHistory(tasks);
		h.setLastUpdated(d);
		System.out.println("History ---> "+h + "\n Task --> " + t);
		try {			
			hdao.update(h);
		}catch(Exception exception) {
			System.out.println(exception);
		}finally {			
			return obj.save(t);
		}
	}
	
	
	@SuppressWarnings("finally")
	@PutMapping("/ChangeStatus/{id}")
	public Task changeStatus(@PathVariable("id") int id, @RequestBody String status) {
		Task t = obj.findbyid(id);
		String s[] = status.split(":");
		String st[] = s[1].split("\"");
		t.setStatus(st[1]);
		
		
		History h = Hrepo.findbyTaskid(t.getId());
		System.out.println("History ---> "+h + "\n Task --> " + t);
		List<TaskHistoryModel> tasks = h.getTaskHistory();
		TaskHistoryModel tm = new TaskHistoryModel();
		tm.setReason("Status Changed");
		tm.setTask(t);
		tasks.add(tm);
		h.setTaskHistory(tasks);
		Date d = new Date();
		h.setLastUpdated(d);
		try {			
			hdao.update(h);
		}catch(Exception exception) {
			System.out.println(exception);
		}finally {			
			return obj.save(t);
		}
	}
	
	@GetMapping("/SearchTaskById/{id}")
	public Task SearchTaskById(@PathVariable("id") int id) {
		return obj.findbyid(id);
	}
	
	@GetMapping("/SearchTaskByTitle/{title}")
	public Task SearchByTitle(@PathVariable("title") String title) {
		return obj.findbytitle(title);
	}
	
	@GetMapping("/getAllHistory")
	public List<History> getAllHistory(){
		return Hrepo.findAll();
	}
	
	@GetMapping("/getHistoryByTaskId/{id}")
	public History getHistoryByTaskId(@PathVariable("id") long id) {
		return Hrepo.findbyTaskid(id);
	}
}
