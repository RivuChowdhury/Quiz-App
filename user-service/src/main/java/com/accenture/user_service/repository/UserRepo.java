package com.accenture.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.user_service.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

}
