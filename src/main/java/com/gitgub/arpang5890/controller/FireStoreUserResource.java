package com.gitgub.arpang5890.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitgub.arpang5890.dao.User;
import com.gitgub.arpang5890.dto.IdDto;
import com.gitgub.arpang5890.dto.UserSearch;
import com.gitgub.arpang5890.service.FireStoreUserService;

@RestController
@RequestMapping(path = "/firestore/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FireStoreUserResource {

	@Autowired
	private FireStoreUserService fireStoreUserService;

	@PostMapping
	public IdDto saveUser(@RequestBody User user) {
		return fireStoreUserService.saveUser(user);
	}

	@PutMapping(path = "/{userId}")
	public void updateUser(@PathVariable String userId, @RequestBody User user) {
		fireStoreUserService.updateUser(userId, user);
	}

	@GetMapping(path = "/{userId}")
	public User getUser(@PathVariable String userId) {
		return fireStoreUserService.getUser(userId);
	}

	// return all data
	@GetMapping
	public List<User> getAllUsers() {
		return fireStoreUserService.getAllUsers();
	}

	@PostMapping(path = "/search")
	public List<User> searchUsers(@RequestBody UserSearch userSearch) {
		return fireStoreUserService.searchUsers(userSearch);
	}

	@DeleteMapping(path = "/{userId}")
	public void deleteUser(@PathVariable String userId) {
		fireStoreUserService.deleteUser(userId);
	}

}
