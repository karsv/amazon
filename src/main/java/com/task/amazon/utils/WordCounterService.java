package com.task.amazon.utils;

import java.util.Map;

public interface WordCounterService {
    Map<String, Integer> countWordsInString(String string);
}
