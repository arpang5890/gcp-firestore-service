package com.gitgub.arpang5890.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitgub.arpang5890.dao.FireStoreUserDao;
import com.gitgub.arpang5890.dao.User;
import com.gitgub.arpang5890.dto.IdDto;
import com.gitgub.arpang5890.dto.UserSearch;

@Service
public class FireStoreUserService {

	@Autowired
	private FireStoreUserDao fireStoreUserDao;

	public IdDto saveUser(final User user) {
		String newUserId = this.fireStoreUserDao.saveUser(toMap(user));
		return new IdDto(newUserId);
	}

	public void updateUser(final String userId, final User user) {
		this.fireStoreUserDao.updateUser(userId, toMap(user));
	}

	public void deleteUser(final String userId) {
		this.fireStoreUserDao.deleteUser(userId);
	}

	public User getUser(final String userId) {
		return this.fireStoreUserDao.getUser(userId);
	}

	public List<User> getAllUsers() {
		return this.fireStoreUserDao.getAllUsers();
	}

	public List<User> searchUsers(final UserSearch userSearch) {
		return this.fireStoreUserDao.searchUsers(userSearch);
	}

	public Map<String, Object> toMap(final User user) {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("name", user.getName());
		userMap.put("gender", user.getGender().toString());
		userMap.put("age", user.getAge());
		userMap.put("emailId", user.getEmailId());
		userMap.put("phoneNumber", user.getPhoneNumber());
		return userMap;
	}
}
