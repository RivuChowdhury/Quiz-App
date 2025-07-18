package com.accenture.course_service.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.course_service.model.QuestionModel;


@FeignClient(name="question-service")
public interface QuestionClient {
	@GetMapping("/questionservice/quiz/{id}")
	public List<QuestionModel> getQuestionByCourseById(@PathVariable("id") int courseId);
	
	@GetMapping("/questionservice/question")
	public List<QuestionModel> getQuestionsByIds(@RequestParam("ids") List<Integer> ids);

}
