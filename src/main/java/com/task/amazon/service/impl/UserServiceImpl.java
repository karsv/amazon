package com.task.amazon.service.impl;

import com.task.amazon.entities.User;
import com.task.amazon.repository.UserEntityRepository;
import com.task.amazon.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public void add(User user) {
        userEntityRepository.save(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userEntityRepository.getUserByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userEntityRepository.findById(id);
    }
}
