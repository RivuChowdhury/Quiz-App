package com.accenture.course_service.model;

import java.util.List;

public class CourseModel {
	private int courseId;
	private String courseName;
	
	/*private List<QuestionModel> questionModel;
	
	public List<QuestionModel> getQuestionModel() {
		return questionModel;
	}
	
	public void setQuestionModel(List<QuestionModel> questionModel) {
		this.questionModel = questionModel;
	}*/
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
