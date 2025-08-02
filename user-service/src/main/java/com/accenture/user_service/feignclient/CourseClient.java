package com.accenture.user_service.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.user_service.model.AnswerModel;
import com.accenture.user_service.model.CourseModel;
import com.accenture.user_service.model.QuizModel;
import com.accenture.user_service.model.UserIdRequest;


@FeignClient(name="course-service")
public interface CourseClient {
	@PostMapping("course-service/courses/enroll/{courseId}")
	public CourseModel enrollForCourses(@RequestBody UserIdRequest userIdRequest,@PathVariable("courseId") int courseId);

	@PostMapping("course-service/createquiz/{courseId}")
	public QuizModel createQuiz(@RequestBody UserIdRequest userIdRequest,@PathVariable("courseId") int courseId);
	
	@PostMapping("course-service/submit/{quizId}")
	public Integer generateResult(@PathVariable("quizId") int quizId,@RequestBody List<AnswerModel> answers);

}
