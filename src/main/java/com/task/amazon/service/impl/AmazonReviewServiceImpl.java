package com.task.amazon.service.impl;

import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import com.task.amazon.entities.AmazonProductComments;
import com.task.amazon.repository.AmazonEntityRepository;
import com.task.amazon.service.AmazonReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AmazonReviewServiceImpl implements AmazonReviewService {
    @Autowired
    private AmazonEntityRepository amazonEntityRepository;

    @Override
    public List<AmazonProductComments> getAllComments() {
        return amazonEntityRepository.getAllComments();
    }

    @Override
    public List<AmazonMostCommentedProduct> findMostCommentProducts(PageRequest pageRequest) {
        return amazonEntityRepository.findMostCommentProducts(pageRequest);
    }

    @Override
    public List<AmazonBestUsersEntity> findActiveUsers(PageRequest pageRequest) {
        return amazonEntityRepository.findActiveUsers(pageRequest);
    }
}
