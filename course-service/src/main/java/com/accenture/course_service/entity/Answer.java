package com.accenture.course_service.entity;

//import javax.persistence.Embeddable;

//@Embeddable
public class Answer {
	private int questionId;
	private String givenAnswer;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getGivenAnswer() {
		return givenAnswer;
	}
	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}

}
