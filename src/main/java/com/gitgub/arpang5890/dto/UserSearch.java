package com.gitgub.arpang5890.dto;

import com.gitgub.arpang5890.dao.Gender;

public class UserSearch {

	private Integer age;

	private Gender gender;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
