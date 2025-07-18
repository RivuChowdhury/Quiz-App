package com.accenture.course_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.course_service.entity.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer>{


}
