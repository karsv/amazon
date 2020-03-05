package com.task.amazon.utils.impl;

import com.task.amazon.dto.WordCommentCountDto;
import com.task.amazon.entities.AmazonProductComments;
import com.task.amazon.utils.WordCounterService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class WordCounterServiceMapImpl implements WordCounterService {
    @Override
    public List<WordCommentCountDto> countWordsInString(
            List<AmazonProductComments> productComments) {
        Map<String, Integer> map = new HashMap<>();
        for (AmazonProductComments s : productComments) {
            String[] words = s.getText()
                    .toLowerCase()
                    .replaceAll("[^a-z]", ",")
                    .split(",");
            for (String word : words) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
        }
        map.remove("");
        return convertMapToListDto(map);
    }

    private List<WordCommentCountDto> convertMapToListDto(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(1000)
                .map(word -> {
                    WordCommentCountDto wordCommentCountDto = new WordCommentCountDto();
                    wordCommentCountDto.setWord(word.getKey());
                    wordCommentCountDto.setCount(word.getValue());
                    return wordCommentCountDto;
                })
                .collect(Collectors.toList());
    }
}
