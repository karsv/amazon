package com.task.amazon.service;

import com.task.amazon.dto.WordCommentCountDto;
import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface AmazonReviewService {
    List<WordCommentCountDto> getPopularWordsFromComment(int page, int pageSize);

    List<AmazonMostCommentedProduct> findMostCommentProducts(PageRequest pageRequest);

    List<AmazonBestUsersEntity> findActiveUsers(PageRequest pageRequest);

    void countWordsInComments();
}
