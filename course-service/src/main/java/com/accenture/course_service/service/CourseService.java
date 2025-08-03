package com.accenture.course_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.accenture.course_service.entity.Answer;
import com.accenture.course_service.entity.Courses;
import com.accenture.course_service.entity.Quiz;
import com.accenture.course_service.entity.UserEnrollment;
import com.accenture.course_service.feignclient.QuestionClient;
import com.accenture.course_service.model.AnswerModel;
import com.accenture.course_service.model.CourseModel;
import com.accenture.course_service.model.QuestionModel;
import com.accenture.course_service.model.QuizModel;
import com.accenture.course_service.repository.CourseRepo;
import com.accenture.course_service.repository.QuizRepo;
import com.accenture.course_service.repository.UserEnrollmentRepo;

@Service
public class CourseService {
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private UserEnrollmentRepo userEnrollmentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private QuestionClient questionClient;
	
	//NOTE: questionModel to be added inside courseModel
	public List<CourseModel> getAllCourses(){
		List<Courses> courses= courseRepo.findAll();
		//List<CourseModel> courseModel=(List<CourseModel>) modelMapper.map(courses, CourseModel.class);
		CourseModel[] courseModelMap=modelMapper.map(courses, CourseModel[].class);
		List<CourseModel> courseModel=Arrays.asList(courseModelMap);
		return courseModel;
	}

	public CourseModel getCourseById(int id) {
		//Courses course=courseRepo.findById(id).get();
		Courses course=courseRepo.findByCourseId(id).get();
		CourseModel courseModel=modelMapper.map(course, CourseModel.class);
		List<QuestionModel> questionModel= webClient.get()
                                             .uri("/question/"+id)
                                             .retrieve()
                                             .bodyToMono(new ParameterizedTypeReference<List<QuestionModel>>() {})
                                             .block();
		//List<QuestionModel> questionModel = (List<QuestionModel>) questionClient.getQuestionByCourseById(id);
		//courseModel.setQuestionModel(questionModel);

		return courseModel;
	}

	//public List<QuestionModel> createQuiz(CourseModel courseModel) 
	public QuizModel createQuiz(int userId,int courseId){
		//int courseId=courseModel.getCourseId();
		/*List<QuestionModel> questions= webClient.get()
                                       .uri("/quiz/"+courseId)
                                       .retrieve()
                                       .bodyToMono(new ParameterizedTypeReference<List<QuestionModel>>() {})
                                       .block();*/
		
		List<QuestionModel> questions = questionClient.getQuestionByCourseById(courseId);
		
		List<Integer> questionIds = questions.stream()
	            .map(QuestionModel::getQuestionId)
	            .collect(Collectors.toList());
		
		//Courses course = courseRepo.findById(courseId).get();
		Courses course = courseRepo.findByCourseId(courseId).get();

		Quiz quiz=new Quiz();
		
		 int attemptedQuizId = quizRepo.findTopByOrderByQuizIdDesc()
				                  .map(Quiz::getQuizId).orElse(0) + 1;
	    quiz.setQuizId(attemptedQuizId);
			    
		quiz.setQuestionId(questionIds);
		quiz.setCourses(course);
		quiz.setUserId(userId);
	    quizRepo.save(quiz);
	    
	    QuizModel quizModel=new QuizModel();
	    quizModel.setQuizId(quiz.getQuizId());
	    quizModel.setQuestions(questions);
		
		return quizModel;
	}
	
	public int generateResult(int quizId,List<AnswerModel> answers) {
		//Quiz quiz = quizRepo.findById(quizId).get();
		Quiz quiz = quizRepo.findByQuizId(quizId).get();
		List<Integer> questionIds=quiz.getQuestionId();
		
		/*String idsParam = questionIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
		
		List<QuestionModel> questions = webClient.get()
		        .uri(uriBuilder -> uriBuilder
		            .path("/question")
		            .queryParam("ids", idsParam)
		            .build())
		        .retrieve()
		        .bodyToMono(new ParameterizedTypeReference<List<QuestionModel>>() {})
		        .block();*/
		
		List<QuestionModel> questions = questionClient.getQuestionsByIds(questionIds);
		
		int result = 0;

		for (AnswerModel answer : answers) {
		    for (QuestionModel question : questions) {
		        if (question.getQuestionId() == answer.getQuestionId()) {
		            String correctAnswer = question.getCorrectAnswer();
		            //System.out.println("User Answer: Q" + answer.getQuestionId() + " -> " + answer.getGivenAnswer());
		           // System.out.println("Correct Answer: " + correctAnswer);

		            if (correctAnswer != null && correctAnswer.trim().equalsIgnoreCase(answer.getGivenAnswer().trim())) {
		                result += 3;
		            }
		            break; 
		        }
		    }
		}
		
		List<Answer> submittedAnswers=answers.stream().map(ans->{
			Answer answer=new Answer();
			answer.setQuestionId(ans.getQuestionId());
			answer.setGivenAnswer(ans.getGivenAnswer());
			return answer;
		}).collect(Collectors.toList());
		
		quiz.setAnswers(submittedAnswers);
		quiz.setResult(result);
		
		quizRepo.save(quiz);
		
		return result;
	}

	public CourseModel enrollForCourses(int userId, int courseId) {
		//Courses course = courseRepo.findById(courseId).get();
		//Courses course = courseRepo.findByCourseId(courseId).get();
		Courses course = courseRepo.findByCourseId(courseId)
			    .orElseThrow(() -> new RuntimeException("Course not found with id " + courseId));

		CourseModel courseModel=modelMapper.map(course, CourseModel.class);
		UserEnrollment userEnrollment=new UserEnrollment();
		
		int userEnrollmentId = userEnrollmentRepo.findTopByOrderByEnrollmentIdDesc()
		                                         .map(UserEnrollment::getEnrollmentId)
		                                         .orElse(0) + 1;
		userEnrollment.setEnrollmentId(userEnrollmentId);
		    
		userEnrollment.setCourseId(courseId);
		userEnrollment.setUserId(userId);
		userEnrollmentRepo.save(userEnrollment);
		
		return courseModel;
	}

}
