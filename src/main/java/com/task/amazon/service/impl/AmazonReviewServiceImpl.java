package com.task.amazon.service.impl;

import com.task.amazon.dto.WordCommentCountDto;
import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import com.task.amazon.entities.WordCountEntity;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.repository.WordCountRepository;
import com.task.amazon.service.AmazonReviewService;
import com.task.amazon.utils.WordCounterService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AmazonReviewServiceImpl implements AmazonReviewService {
    private final AmazonEntityRepository amazonEntityRepository;

    private final WordCounterService wordCounterService;

    private final WordCountRepository wordCountRepository;

    public AmazonReviewServiceImpl(AmazonEntityRepository amazonEntityRepository,
                                   WordCounterService wordCounterService,
                                   WordCountRepository wordCountRepository) {
        this.amazonEntityRepository = amazonEntityRepository;
        this.wordCounterService = wordCounterService;
        this.wordCountRepository = wordCountRepository;
    }

    @Override
    public List<WordCommentCountDto> getPopularWordsFromComment(int page, int pageSize) {
        List<WordCommentCountDto> result = wordCounterService
                .countWordsInString(amazonEntityRepository.getAllComments());
        return getPage(result, page, pageSize);
    }

    @Override
    public List<AmazonMostCommentedProduct> findMostCommentProducts(PageRequest pageRequest) {
        return amazonEntityRepository.findMostCommentProducts(pageRequest);
    }

    @Override
    public List<AmazonBestUsersEntity> findActiveUsers(PageRequest pageRequest) {
        return amazonEntityRepository.findActiveUsers(pageRequest);
    }

    @Override
    public void countWordsInComments() {
        List<WordCountEntity> list = wordCounterService
                .countWordsInString(amazonEntityRepository
                        .getAllComments())
                .stream()
                .map(w -> {
                    WordCountEntity wordCountEntity = new WordCountEntity();
                    wordCountEntity.setWord(w.getWord());
                    wordCountEntity.setNumber(Long.valueOf(w.getCount()));
                    return wordCountEntity;
                })
                .collect(Collectors.toList());
        wordCountRepository.saveAll(list);
    }

    private <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }

        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
}
