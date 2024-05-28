package com.zosh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.models.User;
import com.zosh.repostry.Userrepostry;

@RestController
public class UserController {
	@Autowired
	Userrepostry userrepostry;
	
	@PostMapping("/user")
	public User addUsers(@RequestBody User user) {
		System.out.println(user);
		
		User newUser=new User();
		newUser.setId(user.getId());
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		User savedUser=userrepostry.save(newUser);
		
		
		return savedUser;
		
	}
	 @GetMapping("/users")
	    public List<User> getAllUsers() {
	        return userrepostry.findAll();
	    }
	 @GetMapping("user/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) throws Exception {
		   Optional<User> user = userrepostry.findById(id);
	        if (user.isPresent()) {
	            return ResponseEntity.ok().body(user.get());
	        } else {
	            throw new Exception("User not found with id " + id);
	        }
		 
		
	    }
	 
	 @DeleteMapping("user/{id}")
	    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer id) throws Exception {
		 Optional<User> user = userrepostry.findById(id);
		  if (user.isPresent()) {
			  userrepostry.deleteById(id);
		        Map<String, Boolean> response = new HashMap<>();
		        response.put("deleted", Boolean.TRUE);
		        return response;
//	            return ResponseEntity.ok().body(user.get());
	        } else {
	            throw new Exception("User not found with id " + id);
	        }

	      
	    }
	 
	 @PutMapping("user/{id}")
	 public User updateUser(@PathVariable(value = "id") Integer id,
	                                           @RequestBody User userDetails)throws Exception {
		 Optional<User> user = userrepostry.findById(id);
		 if(user.isEmpty()) {
			 throw new Exception("User not found with id " + id);
			 
		 }
		 User oldUser=user.get();
		 if(userDetails.getFirstName()!=null) {
			 oldUser.setFirstName(userDetails.getFirstName());
		 }
		 if(userDetails.getEmail()!=null) {
			 oldUser.setEmail(userDetails.getEmail());
		 }
		   User updateuser=userrepostry.save(oldUser);
		 
		 
		 return updateuser;
	 }
	 
//	 // Update a user
//	    @PutMapping("/{id}")
//	    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer id,
//	                                           @RequestBody User userDetails) {
//	    	 Optional<User> user = userrepostry.findById(id);
////	    	 User user=new User();     
//
//	        user.setEmail(userDetails.getEmail());
//	        user.setFirstName(userDetails.getFirstName());
//	        user.setLastName(userDetails.getLastName());
//	        user.setPassword(userDetails.getPassword());
//
//	        final User updatedUser = userrepostry.save(user);
//	        return ResponseEntity.ok(updatedUser);
//	    }

//	@GetMapping("/users")
//	public List<User> getUsers() {
//		
//		List<User> users=new ArrayList();
//		
//		User user1=new User(1,"rahul","kumar","rahul@gmail.com","12345");
//		
//		users.add(user1);
//		
//		
//		return users;
//	}
	
//	@GetMapping("/users/{id}")
//	public User getUserByid(@PathVariable("id")Integer id) {
//	 
//		
//		
//		
//		
//		
//		return null;
//	}
//	
//	
//	@PostMapping("/user")
//	public User addUsers(@RequestBody User user) {
//		System.out.println(user);
//		
//		User newUser=new User();
//		newUser.setId(user.getId());
//		newUser.setEmail(user.getEmail());
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastName(user.getLastName());
//		newUser.setPassword(user.getPassword());
//		
//		
//		
//		return newUser;
//		
//	}

}
