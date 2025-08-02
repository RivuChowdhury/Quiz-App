package com.accenture.course_service.model;

import java.util.List;

public class QuizModel {
	private int quizId;
	private List<QuestionModel> questions;
	 
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public List<QuestionModel> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}
	 

}
