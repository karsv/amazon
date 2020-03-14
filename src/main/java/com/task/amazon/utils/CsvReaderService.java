package com.task.amazon.utils;

import com.task.amazon.entities.AmazonReviewEntity;
import java.util.List;

public interface CsvReaderService {
    List<AmazonReviewEntity> parseCsvFile(String path);
}
