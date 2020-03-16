package com.task.amazon.utils.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.task.amazon.entities.AmazonReviewEntity;
import com.task.amazon.entities.PojoAmazonReviewEntity;
import com.task.amazon.exceptions.DataProcessingException;
import com.task.amazon.utils.CsvReaderService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CsvReaderJacksonCsvImpl implements CsvReaderService {
    @Override
    public List<AmazonReviewEntity> parseCsvFile(String path) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder()
                .addColumn("id")
                .addColumn("productId")
                .addColumn("userId")
                .addColumn("profileName")
                .addColumn("helpfulnessNumerator")
                .addColumn("helpfulnessDenominator")
                .addColumn("score")
                .addColumn("time")
                .addColumn("summary")
                .addColumn("text")
                .setSkipFirstDataRow(true)
                .build();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        ObjectReader reader = mapper.readerFor(PojoAmazonReviewEntity.class).with(schema);
        List<PojoAmazonReviewEntity> pojolist = null;

        try {
            pojolist = reader
                    .<PojoAmazonReviewEntity>readValues(new File(path)).readAll();
        } catch (IOException e) {
            throw new DataProcessingException("IO exception while parse CSV file", e);
        }

        return pojolist.stream()
                .map(pojo -> {
                    AmazonReviewEntity amazonReviewEntity = new AmazonReviewEntity();
                    amazonReviewEntity.setId(pojo.getId());
                    amazonReviewEntity.setUserId(pojo.getUserId());
                    amazonReviewEntity.setProductId(pojo.getProductId());
                    amazonReviewEntity.setProfileName(pojo.getProfileName());
                    amazonReviewEntity.setText(pojo.getText());
                    return amazonReviewEntity;
                })
                .collect(Collectors.toList());
    }
}
