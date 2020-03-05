package com.task.amazon.service;

import com.task.amazon.dto.WordCommentCountDto;

import java.util.List;

import org.springframework.data.domain.PageRequest;

public interface CountedWordsService {
    List<WordCommentCountDto> getMostPopularCommentsWord(PageRequest pageRequest);
}
