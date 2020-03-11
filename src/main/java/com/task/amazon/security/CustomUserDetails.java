package com.task.amazon.security;

import com.task.amazon.entities.Role;
import com.task.amazon.entities.User;
import com.task.amazon.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getByEmail(s).get();
        UserBuilder userBuilder;
        if (userService.getByEmail(s).isPresent()) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(s);
            userBuilder.password(user.getPassword());
            userBuilder.roles(getRolesFromSet(user.getRoles()));

        } else {
            throw new UsernameNotFoundException("User not found!");
        }
        return userBuilder.build();
    }

    private String[] getRolesFromSet(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }
}
