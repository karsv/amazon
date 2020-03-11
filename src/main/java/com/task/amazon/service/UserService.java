package com.task.amazon.service;

import com.task.amazon.entities.User;

import java.util.Optional;

public interface UserService {
    void add(User user);

    Optional<User> getByEmail(String email);

    Optional<User> getById(Long id);
}
