package com.task.amazon.controllers;

import com.task.amazon.entities.AmazonReviewEntity;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.utils.impl.CsvParserService;
import com.task.amazon.utils.impl.FileReaderService;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    private static final String PATH = "/home/karsv/Downloads/Reviews.csv";

    @Autowired
    private CsvParserService csvParserService;

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    private AmazonEntityRepository amazonEntityRepository;

    @PostConstruct
    private void postConstruct() {
        List<AmazonReviewEntity> reviewEntityList =
                csvParserService.parseStringsToAmazonReviewEntities(
                        fileReaderService.parseDataToStrings(PATH));
        amazonEntityRepository.saveAll(reviewEntityList);
    }
}
