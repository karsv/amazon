package com.task.amazon.entities;

import lombok.Data;

@Data
public class AmazonBestUsersEntity {
    private String userId;
    private String profileName;
    private Long count;

    public AmazonBestUsersEntity(String userId, String profileName, Long count) {
        this.userId = userId;
        this.profileName = profileName;
        this.count = count;
    }
}
