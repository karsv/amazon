package com.task.amazon.service.impl;

import com.task.amazon.dto.WordCommentCountDto;
import com.task.amazon.repository.WordCountRepository;
import com.task.amazon.service.CountedWordsService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CountedWordServiceImpl implements CountedWordsService {
    @Autowired
    private WordCountRepository wordCountRepository;

    @Override
    public List<WordCommentCountDto> getMostPopularCommentsWord(PageRequest pageRequest) {
        return wordCountRepository.getMostPopularWords(pageRequest)
                .stream()
                .map(word -> {
                    WordCommentCountDto wordCommentCountDto = new WordCommentCountDto();
                    wordCommentCountDto.setWord(word.getWord());
                    wordCommentCountDto.setCount(word.getNumber());
                    return wordCommentCountDto;
                }).collect(Collectors.toList());
    }
}
