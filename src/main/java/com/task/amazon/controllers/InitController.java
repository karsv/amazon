package com.task.amazon.controllers;

import com.task.amazon.configuration.DataConfig;
import com.task.amazon.entities.AmazonReviewEntity;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.utils.impl.CsvParserService;
import com.task.amazon.utils.impl.FileReaderService;
import com.task.amazon.utils.impl.UrlFileGetter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitController {
    private static final String FILE_NAME = "src/main/resources/data.csv";

    private final CsvParserService csvParserService;

    private final FileReaderService fileReaderService;

    private final AmazonEntityRepository amazonEntityRepository;

    private final DataConfig dataConfig;

    public final UrlFileGetter urlFileGetter;

    public InitController(CsvParserService csvParserService,
                          FileReaderService fileReaderService,
                          AmazonEntityRepository amazonEntityRepository,
                          DataConfig dataConfig, UrlFileGetter urlFileGetter) {
        this.csvParserService = csvParserService;
        this.fileReaderService = fileReaderService;
        this.amazonEntityRepository = amazonEntityRepository;
        this.dataConfig = dataConfig;
        this.urlFileGetter = urlFileGetter;
    }

    @PostConstruct
    private void postConstruct() {
        String path;
        if (Files.isReadable(Path.of(dataConfig.getDataPath()))) {
            path = dataConfig.getDataPath();
        } else {
            urlFileGetter.getFileFromUrl(dataConfig.getDataUrl(),
                    dataConfig.getNewPathForDataFile());
            path = dataConfig.getNewPathForDataFile();
        }
        List<AmazonReviewEntity> reviewEntityList =
                csvParserService.parseStringsToAmazonReviewEntities(
                        fileReaderService.parseDataToStrings(dataConfig.getDataPath()));
        amazonEntityRepository.saveAll(reviewEntityList);
    }
}
