package com.task.amazon.controllers;

import com.task.amazon.configuration.DataConfig;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.utils.CsvReaderService;
import com.task.amazon.utils.impl.UrlFileGetter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitController {
    private final AmazonEntityRepository amazonEntityRepository;
    private final DataConfig dataConfig;
    private final UrlFileGetter urlFileGetter;

    public InitController(AmazonEntityRepository amazonEntityRepository,
                          DataConfig dataConfig, UrlFileGetter urlFileGetter,
                          CsvReaderService csvReader) {
        this.amazonEntityRepository = amazonEntityRepository;
        this.dataConfig = dataConfig;
        this.urlFileGetter = urlFileGetter;
        this.csvReader = csvReader;
    }

    private final CsvReaderService csvReader;

    @PostConstruct
    private void postConstruct() throws IOException {
        String path = dataConfig.getDataPath();

        if (!Files.isReadable(Path.of(dataConfig.getDataPath()))) {
            urlFileGetter.getFileFromUrl(dataConfig.getDataUrl(),
                    dataConfig.getNewPathForDataFile());
            path = dataConfig.getNewPathForDataFile();
        }

        amazonEntityRepository.saveAll(csvReader.parseCsvFile(path));
    }
}
