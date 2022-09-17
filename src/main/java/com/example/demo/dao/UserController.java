package com.example.demo.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.User;

@RestController
public class UserController {
	
	@Autowired repo obj;
	
	@PostMapping("/NewUser")
	public User NewUser(@RequestBody User e) {
		System.out.println("Data ---> "+e);
		e.setId(obj.count()+1);
		obj.save(e);
		return e;
	}
	
	
	@GetMapping("/allUser")
	public List<User> allUser(){
		return obj.findAll();
	}
	
	
	@DeleteMapping("/DeleteUser/{id}")
	public Boolean deleteUser(@PathVariable("id") int id) {
		obj.deleteById(id);
		return true;
	}
	
	@PutMapping("/UodateUser")
	public User updateUser(@RequestBody User e) {
		return obj.save(e);
	}

}
