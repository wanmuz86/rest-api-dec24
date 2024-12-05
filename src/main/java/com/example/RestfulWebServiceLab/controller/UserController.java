package com.example.RestfulWebServiceLab.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// RestController - Controller for the API
//  Controller => Class that will be looked by Spring url dispatcher
// to look for Route definition
@RestController
@RequestMapping("/api") // The URL will be prefixed with /api
public class UserController {
	
	// This endpoint can produce data in JSON or XML format
	@GetMapping(value="/users", produces= {"application/json","application/xml"})
	public List<String> getUsers(){
		return new ArrayList<>(Arrays.asList("Alice","Bob","Charlie"));
	}
	
	// @PathVariable -> Compulasary Variable that is part of the url / path
	// eg /users/1 , /users/2 , /user/3
	// localhost:8080/api/users/1 , 
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable("id") int id) {
		List<String> users = Arrays.asList("Alice","Bob","Charlie");
		return users.get(id-1);
	}
	
	// /api/users/sort?order=asc
	// /api/users/sort?order=desc
	// /api/users/sort -> It is not mandatory, -> default = order is asc
	@GetMapping("/users/sort")
	public List<String> getUsersSorted(@RequestParam(value="order",defaultValue="asc") String order){
		List<String> users = Arrays.asList("Alice","Bob","Charlie");
		if ("desc".equals(order)) {
			users.sort(Collections.reverseOrder());
		}
		else {
			Collections.sort(users);
		}
		return users;
	}
	
	// @RequestBody - the data passed by user in the API
	// In the body of the request
	
	
	// @PostMapping to define the method POST of API
	@PostMapping("/users")
	public String createUser(@RequestBody String user) {
		// TODO logic later with DB
		System.out.println("Data received from API "+user);
		return "User "+ user + " created!";
	}
	
	// PUT /users/1, /users/2
	@PutMapping("/users/{id}")
	public String updateUser(@PathVariable("id") int id,
			@RequestBody String user) {
		// TODO logic later with DB
		System.out.println("Data received from API "+user);
		return "User "+user + "updated";
		
	}
	
	// DELETE /users/1, /users/2
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		
		// TODO logic later with DB
		System.out.println("Received id to be deleted "+id);
		return "User successfully deleted";
	}
	
	

}
