package com.accenture.course_service.entity;

import java.util.List;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;*/
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/*@Entity
@Table(name="courses")*/

@Document(collection="courses")
public class Courses {
	
	@Id
    private String id; 
	
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)*/
	@Field("course_id")
	private int courseId;
	@Field("course_name")
	private String courseName;
	
	//@OneToMany
	private List<Quiz> quizzes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Quiz> getQuiz() {
		return quizzes;
	}
	public void setQuiz(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
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
	@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	
	

}
