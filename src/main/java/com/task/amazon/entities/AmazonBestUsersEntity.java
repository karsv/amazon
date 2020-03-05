package com.task.amazon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AmazonBestUsersEntity {
    private String userId;
    private String profileName;
    private Long count;
}
