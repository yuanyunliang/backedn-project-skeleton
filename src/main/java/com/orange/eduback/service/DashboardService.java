package com.orange.eduback.service;

import com.orange.eduback.domain.User;
import com.orange.eduback.dto.UserStatDto;

import java.util.List;

public interface DashboardService {
    Integer getUserCount();

    List<UserStatDto> getUsers();
}
