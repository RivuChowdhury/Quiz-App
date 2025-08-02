package com.accenture.course_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.course_service.entity.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses,Integer> {

}
