package com.task.amazon;

import java.util.List;

import com.task.amazon.utils.ReaderService;
import com.task.amazon.utils.impl.FileReaderService;

public class Main {
    public static void main(String[] args) {
        ReaderService readerService = new FileReaderService();
        List<String> strings = readerService.parse("/home/karsv/Downloads/Reviews.csv");
        System.out.println(strings);
    }
}
