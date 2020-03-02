package com.task.amazon.utils.impl;

import com.task.amazon.exceptions.DataProcessingException;
import com.task.amazon.utils.ReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileReaderService implements ReaderService {

    @Override
    public List<String> parse(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new DataProcessingException("Can't read file!", e);
        }
    }
}
