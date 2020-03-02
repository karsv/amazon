package com.task.amazon.repository;

import com.task.amazon.entities.AmazonReviewEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AmazonEntityRepository extends JpaRepository<AmazonReviewEntity, Long> {
}
