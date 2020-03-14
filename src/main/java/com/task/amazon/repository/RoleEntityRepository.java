package com.task.amazon.repository;

import com.task.amazon.entities.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByName(String name);
}
