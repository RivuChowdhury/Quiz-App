package com.accenture.user_service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.user_service.entity.User;
import com.accenture.user_service.feignclient.CourseClient;
import com.accenture.user_service.model.AnswerModel;
import com.accenture.user_service.model.CourseModel;
import com.accenture.user_service.model.QuizIdRequest;
import com.accenture.user_service.model.QuizModel;
import com.accenture.user_service.model.UserIdRequest;
import com.accenture.user_service.model.UserModel;
import com.accenture.user_service.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CourseClient courseClient;
	
	public List<UserModel> getAllUsers(){
		//return userRepo.findAll();
		List<User> users=userRepo.findAll();
		UserModel[] userModelMap=modelMapper.map(users, UserModel[].class);
		List<UserModel> userModels=Arrays.asList(userModelMap);
		return userModels;
	}
	
	public UserModel getUserById(int id) {
		//return userRepo.findById(id).get();
		User user=userRepo.findById(id).get();
		UserModel userModel=modelMapper.map(user, UserModel.class);
		return userModel;
	}
	
	public User addUser(User user) {
		return userRepo.save(user);
	}

	public UserModel enrollForCourse(int userId,int courseId) {
		User user=userRepo.findById(userId).get();
		List<Integer> enrolledCourses=user.getCourseId();

	    if (!enrolledCourses.contains(courseId)) {
	        enrolledCourses.add(courseId);
	        user.setCourseId(enrolledCourses);
	        userRepo.save(user);
	    }
		UserModel userModel=modelMapper.map(user, UserModel.class);
		userModel.setCourseId(enrolledCourses);
		UserIdRequest userIdrequest=new UserIdRequest();
		userIdrequest.setUserId(userId);
		CourseModel courseModel=courseClient.enrollForCourses(userIdrequest,courseId);
		return userModel;
	}
	
	public QuizModel createQuiz(int userId,int courseId) {
		User user=userRepo.findById(userId).get();
		List<Integer> enrolledCourses=user.getCourseId();
		if(!enrolledCourses.contains(courseId)) {
			return null;
		}
		UserIdRequest userIdrequest=new UserIdRequest();
		userIdrequest.setUserId(userId);
		QuizModel quiz=courseClient.createQuiz(userIdrequest,courseId);
		
		List<Integer> attemptedQuiz=user.getQuizId();
		attemptedQuiz.add(quiz.getQuizId());
		user.setQuizId(attemptedQuiz);
		userRepo.save(user);
		return quiz;
	}
	
	public Integer generateResult(int quizId,List<AnswerModel> answers) {
		//User user=userRepo.findById(userId).get();
		//UserIdRequest userIdRequest=new UserIdRequest();
		//userIdRequest.setUserId(userId);
		
		/*QuizIdRequest quizIdRequest=new QuizIdRequest();
		quizIdRequest.setQuizId(quizId);*/
		Integer result=courseClient.generateResult(quizId,answers);
		return result;
	}

}
