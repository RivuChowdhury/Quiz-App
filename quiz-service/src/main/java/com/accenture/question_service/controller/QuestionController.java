package com.accenture.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.question_service.entity.Question;
import com.accenture.question_service.model.QuestionModel;
import com.accenture.question_service.service.QuestionService;



@RestController
@RequestMapping("questionservice")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("questions")
	public List<QuestionModel> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	/*@GetMapping("question/{id}")
	public QuestionModel getQuestionById(@PathVariable int id) {
		return questionService.getQuestionById(id);		
	}*/
	
	@GetMapping("question")
	public List<QuestionModel> getQuestionsByIds(@RequestParam("ids") List<Integer> ids) {
	    return questionService.getQuestionsByIds(ids);
	}

	
	@PostMapping("question")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
	    Question newquestion=questionService.addQuestion(question);
	    return ResponseEntity.status(HttpStatus.CREATED).body(newquestion);
		
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("quiz/{id}")
	public ResponseEntity<List<QuestionModel>> getQuestionByCourseById(@PathVariable("id") int courseId){
		List<QuestionModel> quizQuestions=questionService.getQuestionByCourseById(courseId);
		return ResponseEntity.status(HttpStatus.CREATED).body(quizQuestions);
	}
	
	

}
