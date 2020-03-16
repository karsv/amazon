package com.task.amazon.utils;

import com.task.amazon.dto.WordCommentCountDto;
import com.task.amazon.entities.AmazonProductComments;
import java.util.List;

public interface WordCounterService {
    List<WordCommentCountDto> countWordsInString(List<AmazonProductComments> productComments);
}
