package com.accenture.user_service.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String email;
	private String password;
	@ElementCollection
	List<Integer> courseId;
	@ElementCollection
	List<Integer> quizId;
	
	
	public List<Integer> getQuizId() {
		return quizId;
	}
	public void setQuizId(List<Integer> quizId) {
		this.quizId = quizId;
	}
	public List<Integer> getCourseId() {
		return courseId;
	}
	public void setCourseId(List<Integer> courseId) {
		this.courseId = courseId;
	}
	public int getUserId() {
		return userId;
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
