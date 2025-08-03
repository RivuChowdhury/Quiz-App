package com.accenture.course_service.repository;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.course_service.entity.Quiz;

@Repository
//public interface QuizRepo extends JpaRepository<Quiz,Integer>
public interface QuizRepo extends MongoRepository<Quiz,String>{
	Optional<Quiz> findByQuizId(int quizId);
	
	Optional<Quiz> findTopByOrderByQuizIdDesc();

}
