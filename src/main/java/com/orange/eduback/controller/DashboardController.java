package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.domain.User;
import com.orange.eduback.dto.UserStatDto;
import com.orange.eduback.service.DashboardService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private DashboardService dashboardService;

    @GetMapping ("/UserCount")
    public PlainResult<List<UserStatDto>> getUsers() {
        return PlainResult.success(dashboardService.getUsers());
    }
}
