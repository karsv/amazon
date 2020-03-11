package com.task.amazon.service;

import com.task.amazon.entities.Role;

import java.util.Optional;

public interface RoleService {
    void add(Role role);

    Optional<Role> getByName(String roleName);
}
