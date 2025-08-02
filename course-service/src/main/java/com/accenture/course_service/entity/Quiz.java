package com.accenture.course_service.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="quiz")
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int quizId;
	
	@ElementCollection
	private List<Integer> questionId;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Courses course;
	
	private int userId;
	
	@ElementCollection
	private List<Answer> answers;
	
	private int result;
	
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Integer> getQuestionId() {
		return questionId;
	}
	public void setQuestionId(List<Integer> questionId) {
		this.questionId = questionId;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public Courses getCourses() {
		return course;
	}
	public void setCourses(Courses course) {
		this.course = course;
	}
	
	
	
	

}
