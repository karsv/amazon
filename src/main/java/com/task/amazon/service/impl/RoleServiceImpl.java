package com.task.amazon.service.impl;

import com.task.amazon.entities.Role;
import com.task.amazon.repository.RoleEntityRepository;
import com.task.amazon.service.RoleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Override
    public void add(Role role) {
        roleEntityRepository.save(role);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        return roleEntityRepository.getByName(roleName);
    }
}
