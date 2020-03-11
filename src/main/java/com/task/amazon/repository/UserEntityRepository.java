package com.task.amazon.repository;

import com.task.amazon.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
}
