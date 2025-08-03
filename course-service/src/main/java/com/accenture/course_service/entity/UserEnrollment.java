package com.accenture.course_service.entity;

import org.springframework.data.annotation.Id;
/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity

@Document(collection="userenrollments")
public class UserEnrollment {
	@Id
    private String id;
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)*/
    private int enrollmentId;
	private int courseId;
	private int userId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
