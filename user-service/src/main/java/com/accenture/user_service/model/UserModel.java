package com.accenture.user_service.model;

import java.util.List;

public class UserModel {
	int userId;
	String username;
	String email;
	String password;
	List<Integer> courseId;
	//List<QuizModel> quizModel;
	
	
	public int getUserId() {
		return userId;
	}
	public List<Integer> getCourseId() {
		return courseId;
	}
	public void setCourseId(List<Integer> courseId) {
		this.courseId = courseId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
