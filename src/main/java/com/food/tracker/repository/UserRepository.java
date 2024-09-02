package com.food.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.tracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String userName, String password);

	User findByUserName(String userName);

}
