package com.accenture.course_service.repository;

import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.accenture.course_service.entity.UserEnrollment;

//public interface UserEnrollmentRepo extends JpaRepository<UserEnrollment,Integer> 
public interface UserEnrollmentRepo extends MongoRepository<UserEnrollment,String>{
	Optional<UserEnrollment> findTopByOrderByEnrollmentIdDesc();

}
