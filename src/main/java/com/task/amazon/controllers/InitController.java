package com.task.amazon.controllers;

import com.task.amazon.configuration.DataConfig;
import com.task.amazon.entities.AmazonReviewEntity;
import com.task.amazon.exceptions.DataProcessingException;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.utils.impl.CsvParserService;
import com.task.amazon.utils.impl.FileReaderService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitController {
    private static final String FILE_NAME = "/src/main/resources/dummy.csv";

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
        String path;
        if (Files.isReadable(Path.of(dataConfig.getDataPath()))) {
            path = dataConfig.getDataPath();
        } else {
            try {
                ReadableByteChannel readableByteChannel = Channels
                        .newChannel(new URL(dataConfig.getDataUrl()).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel()
                        .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                path = FILE_NAME;
            } catch (IOException e) {
                throw new DataProcessingException("Can't get data from url!", e);
            }
        }
        List<AmazonReviewEntity> reviewEntityList =
                csvParserService.parseStringsToAmazonReviewEntities(
                        fileReaderService.parseDataToStrings(dataConfig.getDataPath()));
        amazonEntityRepository.saveAll(reviewEntityList);
    }
}
