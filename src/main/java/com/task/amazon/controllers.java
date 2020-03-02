package com.task.amazon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class controllers {

    @GetMapping
    public String test(){
        return "Test!";
    }
}
