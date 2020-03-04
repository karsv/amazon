package com.task.amazon.controllers;

import com.task.amazon.entities.AmazonBestUsersEntity;
import com.task.amazon.entities.AmazonMostCommentedProduct;
import com.task.amazon.entities.AmazonProductComments;
import com.task.amazon.service.AmazonReviewService;
import com.task.amazon.utils.impl.WordCounterServiceMapImpl;

import java.util.List;
import java.util.Map;

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
    private WordCounterServiceMapImpl wordCounterServiceMap;

    @GetMapping("/users")
    public List<AmazonBestUsersEntity> getBestUsers(@RequestParam(name = "page") int page) {
        return amazonReviewService.findActiveUsers(PageRequest
                .of(page, 100, JpaSort.unsafe(Sort.Direction.DESC, "COUNT(a.userId)")));
    }

    @GetMapping(value = "/products")
    public List<AmazonMostCommentedProduct> getMostCommentedProducts(
            @RequestParam(name = "page") int page) {
        return amazonReviewService.findMostCommentProducts(PageRequest
                .of(page, 100,
                        JpaSort.unsafe(Sort.Direction.DESC, "COUNT(a.productId)")));
    }

    @GetMapping("/words")
    public Map<String, Integer> getPopularWords() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AmazonProductComments apc : amazonReviewService.getAllComments()) {
            stringBuilder.append(apc.getText().toLowerCase());
        }
        Map<String, Integer> map =
                wordCounterServiceMap.countWordsInString(stringBuilder.toString());
        return map;
    }

}
