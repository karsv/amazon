package com.task.amazon.utils.impl;

import com.task.amazon.entities.AmazonReviewEntity;
import com.task.amazon.utils.ParseService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CsvParserService implements ParseService {
    @Override
    public List<AmazonReviewEntity> parseStringsToAmazonReviewEntities(List<String> strings) {
        List<AmazonReviewEntity> list = new ArrayList<>();
        AmazonReviewEntity amazonReviewEntity;
        for (int i = 1; i < strings.size(); i++) {
            amazonReviewEntity = new AmazonReviewEntity();
            String[] s = strings.get(i).split(",");
            amazonReviewEntity.setId(Long.valueOf(s[0]));
            amazonReviewEntity.setProductId(s[1]);
            amazonReviewEntity.setUserId(s[2]);
            amazonReviewEntity.setProfileName(s[3]);
            amazonReviewEntity.setText(s[9]);
            list.add(amazonReviewEntity);
        }
        return list;
    }
}
