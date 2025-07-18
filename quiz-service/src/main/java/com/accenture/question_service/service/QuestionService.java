package com.accenture.question_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.question_service.entity.Question;
import com.accenture.question_service.model.QuestionModel;
import com.accenture.question_service.repository.QuestionRepo;



@Service
public class QuestionService {
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<QuestionModel> getAllQuestions() {
		List<Question> questions=questionRepo.findAll();
		//List<QuestionModel> questionModel=(List<QuestionModel>) modelMapper.map(questions, QuestionModel.class);
		List<QuestionModel> questionModels=Arrays.asList(modelMapper.map(questions, QuestionModel[].class));
		return questionModels;
	}
	
	public Question addQuestion(Question question) {
		return questionRepo.save(question);
	}

	/*public QuestionModel getQuestionById(int id) {
		Question question=questionRepo.findById(id).get();
		QuestionModel questionModel=modelMapper.map(question, QuestionModel.class);
		return questionModel;
	}*/
	

	public List<QuestionModel> getQuestionByCourseById(int courseId) {
		List<Question> questions = questionRepo.findByCourseId(courseId);
	    //List<QuestionModel> quizQuestions=(List<QuestionModel>) modelMapper.map(questions, QuestionModel.class);
		List<QuestionModel> quizQuestions = questions.stream()
		        .map(q -> modelMapper.map(q, QuestionModel.class))
		        .collect(Collectors.toList());
		return quizQuestions;
	}

	public List<QuestionModel> getQuestionsByIds(List<Integer> ids) {
		 List<Question> questions = questionRepo.findAllById(ids);
		    
		 return questions.stream()
		        .map(question -> modelMapper.map(question, QuestionModel.class))
		        .collect(Collectors.toList());
	}

	

}
