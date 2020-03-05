package com.task.amazon.entities;

import lombok.Data;

@Data
public class AmazonMostCommentedProduct {
    private String productId;
    private Long count;

    public AmazonMostCommentedProduct(String productId, Long count) {
        this.productId = productId;
        this.count = count;
    }
}
