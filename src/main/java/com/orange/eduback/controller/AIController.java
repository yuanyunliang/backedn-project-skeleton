package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.service.AIService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Resource
    private AIService aiService;

    @GetMapping("/query")
    public PlainResult<String> query(@RequestParam String query) {
        return PlainResult.success(aiService.query(query));
    }
}
