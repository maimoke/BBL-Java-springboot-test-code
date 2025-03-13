package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;
import java.util.Optional;
@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path="/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path="/users/{id}")
	public @ResponseBody Optional<User> getUserFromId(@PathVariable("id") Integer id) {


		return userRepository.findById(id);
	}

	@PostMapping(path="/users")
	public ResponseEntity addUser(@RequestBody UserForm userForm) {
		User user = new User();
		user.setName(userForm.name);
		user.setUsername(userForm.username);
		user.setEmail(userForm.email);
		user.setAddress(userForm.address);
		user.setPhone(userForm.phone);
		user.setWebsite(userForm.website);
		user.setCompany(userForm.company);
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping(path="/users/{id}")
	public ResponseEntity updateUser(@PathVariable("id") Integer id,@RequestBody UserForm userForm){
		return userRepository.findById(id)
		.map(user -> {
			user.setName(userForm.name);
			user.setUsername(userForm.username);
			user.setEmail(userForm.email);
			user.setAddress(userForm.address);
			user.setPhone(userForm.phone);
			user.setWebsite(userForm.website);
			user.setCompany(userForm.company);
			userRepository.save(user);
		  return ResponseEntity.ok(HttpStatus.OK);
		})
		.orElseGet(() -> {
		  return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
		});
	}

	@DeleteMapping(path="/users/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Integer id){
		return userRepository.findById(id)
		.map(user -> {
			userRepository.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		})
		.orElseGet(() -> {
			return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
		});
	}
}