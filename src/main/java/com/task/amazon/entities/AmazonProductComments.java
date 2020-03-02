package com.task.amazon.entities;

import lombok.Data;

@Data
public class AmazonProductComments {
    String text;

    public AmazonProductComments(String text) {
        this.text = text;
    }
}
