package com.accenture.course_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.course_service.model.AnswerModel;
import com.accenture.course_service.model.CourseModel;
import com.accenture.course_service.model.QuestionModel;
import com.accenture.course_service.model.QuizModel;
import com.accenture.course_service.model.UserIdRequest;
import com.accenture.course_service.service.CourseService;

@RestController
@RequestMapping("course-service")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("courses")
	public List<CourseModel> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	@GetMapping("courses/{id}")
	public CourseModel getCourseById(@PathVariable int id) {
		//Optional<CourseModel> courseModel=
		return courseService.getCourseById(id);
		
	}
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@PostMapping("createquiz/{courseId}")
	//public ResponseEntity<List<QuestionModel>> createQuiz(@RequestBody CourseModel courseModel)
	public QuizModel createQuiz(@RequestBody UserIdRequest userIdRequest,@PathVariable("courseId") int courseId){
		//List<QuestionModel> quiz= courseService.createQuiz(courseModel);
		QuizModel quiz=courseService.createQuiz(userIdRequest.getUserId(),courseId);
		//return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
		return quiz;
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@PostMapping("submit/{quizId}")
	public Integer generateResult(@PathVariable("quizId") int quizId,@RequestBody List<AnswerModel> answers){
	    int result=courseService.generateResult(quizId,answers);
		//return ResponseEntity.status(HttpStatus.OK).body(result);	
	    return result;
	}
	
	@PostMapping("courses/enroll/{courseId}")
	public CourseModel enrollForCourses(@RequestBody UserIdRequest userIdRequest,@PathVariable("courseId") int courseId){
		return courseService.enrollForCourses(userIdRequest.getUserId(),courseId);
	}

}
