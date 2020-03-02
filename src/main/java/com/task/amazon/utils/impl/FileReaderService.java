package com.task.amazon.utils.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.task.amazon.utils.ReaderService;
import org.springframework.stereotype.Service;

@Service
public class FileReaderService implements ReaderService {

    @Override
    public List<String> parse(String path){
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }
}
