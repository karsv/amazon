package com.task.amazon.controllers;

import com.task.amazon.dto.WordCommentCountDto;
import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import com.task.amazon.service.AmazonReviewService;
import com.task.amazon.service.CountedWordsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazon")
public class AmazonReviewController {
    @Autowired
    private AmazonReviewService amazonReviewService;

    @Autowired
    private CountedWordsService countedWordsService;

    @GetMapping("/users")
    public List<AmazonBestUsersEntity> getBestUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "50") int limit) {
        return amazonReviewService.findActiveUsers(PageRequest
                .of(page, limit, JpaSort.unsafe(Sort.Direction.DESC, "COUNT(a.userId)")));
    }

    @GetMapping(value = "/products")
    public List<AmazonMostCommentedProduct> getMostCommentedProducts(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "50") int limit) {
        return amazonReviewService.findMostCommentProducts(PageRequest
                .of(page, limit,
                        JpaSort.unsafe(Sort.Direction.DESC, "COUNT(a.productId)")));
    }

    @GetMapping("/words")
    public List<WordCommentCountDto> getPopularWords(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "50") int limit) {
        return countedWordsService.getMostPopularCommentsWord(PageRequest
        .of(page, limit));
    }
}
