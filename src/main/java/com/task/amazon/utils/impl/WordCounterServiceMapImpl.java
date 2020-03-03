package com.task.amazon.utils.impl;

import static java.util.stream.Collectors.toMap;

import com.task.amazon.utils.WordCounterService;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class WordCounterServiceMapImpl implements WordCounterService {
    @Override
    public Map<String, Integer> countWordsInString(String string) {
        Map<String, Integer> map = new HashMap<>();
        String[] words = string.replaceAll("[^a-z]", ",").split(",");
        for (String s : words) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        map.remove("");
        Map<String, Integer> sorted = map.entrySet().parallelStream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(1000)
                .collect(toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        return sorted;
    }
}
