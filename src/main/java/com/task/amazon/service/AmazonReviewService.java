package com.task.amazon.service;

import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import com.task.amazon.entities.AmazonProductComments;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface AmazonReviewService {
    List<AmazonProductComments> getAllComments();

    List<AmazonMostCommentedProduct> findMostCommentProducts(PageRequest pageRequest);

    List<AmazonBestUsersEntity> findActiveUsers(PageRequest pageRequest);
}
