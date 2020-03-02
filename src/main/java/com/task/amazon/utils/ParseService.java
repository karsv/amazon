package com.task.amazon.utils;

import com.task.amazon.entities.AmazonReviewEntity;

import java.util.List;

public interface ParseService {
    List<AmazonReviewEntity> parseStringsToAmazonReviewEntities(List<String> strings);
}
