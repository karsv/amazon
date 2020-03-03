package com.task.amazon.repository;

import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import com.task.amazon.entities.AmazonProductComments;
import com.task.amazon.entities.AmazonReviewEntity;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AmazonEntityRepository extends JpaRepository<AmazonReviewEntity, Long> {

    @Query("SELECT new com.task.amazon.entities.AmazonBestUsersEntity(a.userId, a.profileName, "
            + "COUNT(a.userId))FROM AmazonReviewEntity AS a "
            + "GROUP BY a.userId, a.profileName ORDER BY COUNT(a.userId) DESC")
    List<AmazonBestUsersEntity> findActiveUsers(PageRequest pageRequest);

    @Query("SELECT new com.task.amazon.entities.AmazonMostCommentedProduct(a.productId, "
            + "COUNT(a.productId))FROM AmazonReviewEntity AS a "
            + "GROUP BY a.productId ORDER BY COUNT(a.productId) DESC")
    List<AmazonMostCommentedProduct> findMostCommentProducts(PageRequest pageRequest);

    @Query("SELECT new com.task.amazon.entities.AmazonProductComments(a.text)"
            + "FROM AmazonReviewEntity AS a")
    List<AmazonProductComments> getAllComments();
}
