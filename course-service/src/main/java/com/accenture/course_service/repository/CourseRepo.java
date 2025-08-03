package com.accenture.course_service.repository;

import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.course_service.entity.Courses;

@Repository
//public interface CourseRepo extends JpaRepository<Courses,Integer> 
public interface CourseRepo extends MongoRepository<Courses,String>{
	Optional<Courses> findByCourseId(int courseId);
}
