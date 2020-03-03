package com.task.amazon.controllers;

import com.task.amazon.configuration.DataConfig;
import com.task.amazon.entities.AmazonReviewEntity;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.utils.impl.CsvParserService;
import com.task.amazon.utils.impl.FileReaderService;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitController {
    private final CsvParserService csvParserService;

    private final FileReaderService fileReaderService;

    private final AmazonEntityRepository amazonEntityRepository;

    private final DataConfig dataConfig;

    public InitController(CsvParserService csvParserService,
                          FileReaderService fileReaderService,
                          AmazonEntityRepository amazonEntityRepository,
                          DataConfig dataConfig) {
        this.csvParserService = csvParserService;
        this.fileReaderService = fileReaderService;
        this.amazonEntityRepository = amazonEntityRepository;
        this.dataConfig = dataConfig;
    }

    @PostConstruct
    private void postConstruct() {
        List<AmazonReviewEntity> reviewEntityList =
                csvParserService.parseStringsToAmazonReviewEntities(
                        fileReaderService.parseDataToStrings(dataConfig.getDataPath()));
        amazonEntityRepository.saveAll(reviewEntityList);
    }
}
