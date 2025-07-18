package com.accenture.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.user_service.entity.User;
import com.accenture.user_service.model.AnswerModel;
import com.accenture.user_service.model.CourseEnrollmentRequest;
import com.accenture.user_service.model.QuizModel;
import com.accenture.user_service.model.UserModel;
import com.accenture.user_service.service.UserService;

@RestController
@RequestMapping("user-service")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("users")
	public List<UserModel> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("user/{userId}")
	public UserModel getUserById(@PathVariable("userId") int id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("user")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User addedUser=userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
	}
	
	@PostMapping("user/courses/{userId}")
	public ResponseEntity<UserModel> enrollForCourse(@PathVariable("userId") int userId,@RequestBody CourseEnrollmentRequest courseEnrollmentRequest){
		UserModel user=userService.enrollForCourse(userId,courseEnrollmentRequest.getCourseId());
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PostMapping("user/courses/quiz/{userId}")
	public ResponseEntity<QuizModel> createQuiz(@PathVariable("userId") int userId,@RequestBody CourseEnrollmentRequest courseEnrollmentRequest){
		QuizModel quiz=userService.createQuiz(userId,courseEnrollmentRequest.getCourseId());
		return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
	}
	
	@PostMapping("user/courses/result/{quizId}")
	public Integer generateResult(@PathVariable("quizId") int quizId,@RequestBody List<AnswerModel> answers) {
		return userService.generateResult(quizId,answers);
	}

}
