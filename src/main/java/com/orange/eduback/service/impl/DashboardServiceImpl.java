package com.orange.eduback.service.impl;

import com.orange.eduback.domain.User;
import com.orange.eduback.dto.UserStatDto;
import com.orange.eduback.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Override
    public Integer getUserCount() {
        return 0;
    }

    @Override
    public List<UserStatDto> getUsers() {
        return List.of();
    }
}
