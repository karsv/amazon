package com.task.amazon.controllers;

import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.utils.CsvReaderService;
import com.task.amazon.utils.impl.UrlFileGetter;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    private final AmazonEntityRepository amazonEntityRepository;
    private final UrlFileGetter urlFileGetter;
    private final CsvReaderService csvReader;

    @Value("${data.path}")
    private String dataPath;
    @Value("${data.url}")
    private String urlPath;
    @Value("${data.newpath}")
    private String newDataPath;

    public InitController(AmazonEntityRepository amazonEntityRepository,
                          UrlFileGetter urlFileGetter,
                          CsvReaderService csvReader) {
        this.amazonEntityRepository = amazonEntityRepository;
        this.urlFileGetter = urlFileGetter;
        this.csvReader = csvReader;
    }

    @PostConstruct
    private void postConstruct() {
        String path = dataPath;

        if (!Files.isReadable(Path.of(dataPath))) {
            urlFileGetter.getFileFromUrl(urlPath,
                    newDataPath);
            path = dataPath;
        }

        amazonEntityRepository.saveAll(csvReader.parseCsvFile(path));
    }
}
