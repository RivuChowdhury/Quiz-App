package com.accenture.course_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.course_service.entity.UserEnrollment;

public interface UserEnrollmentRepo extends JpaRepository<UserEnrollment,Integer> {

}
