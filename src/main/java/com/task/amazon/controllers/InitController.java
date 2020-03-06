package com.task.amazon.controllers;

import com.task.amazon.entities.Role;
import com.task.amazon.entities.User;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.service.AmazonReviewService;
import com.task.amazon.service.RoleService;
import com.task.amazon.service.UserService;
import com.task.amazon.utils.CsvReaderService;
import com.task.amazon.utils.impl.UrlFileGetter;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    private final AmazonEntityRepository amazonEntityRepository;
    private final CsvReaderService csvReader;
    private final RoleService roleService;
    private final UrlFileGetter urlFileGetter;
    private final UserService userService;
    private final AmazonReviewService amazonReviewService;
    private final PasswordEncoder passwordEncoder;

    @Value("${data.path}")
    private String dataPath;
    @Value("${data.url}")
    private String urlPath;
    @Value("${data.newpath}")
    private String newDataPath;

    public InitController(AmazonEntityRepository amazonEntityRepository,
                          UrlFileGetter urlFileGetter,
                          CsvReaderService csvReader, RoleService roleService,
                          UserService userService,
                          AmazonReviewService amazonReviewService,
                          PasswordEncoder passwordEncoder) {
        this.amazonEntityRepository = amazonEntityRepository;
        this.urlFileGetter = urlFileGetter;
        this.csvReader = csvReader;
        this.roleService = roleService;
        this.userService = userService;
        this.amazonReviewService = amazonReviewService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void postConstruct() {
        String path = dataPath;

        if (!Files.isReadable(Path.of(dataPath))) {
            urlFileGetter.getFileFromUrl(urlPath, newDataPath);
            path = newDataPath;
        }

        amazonEntityRepository.saveAll(csvReader.parseCsvFile(path));

        amazonReviewService.countWordsInComments();

        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName("USER");
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail("admin@admin.com");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(adminRole);
        userService.add(admin);
    }
}
