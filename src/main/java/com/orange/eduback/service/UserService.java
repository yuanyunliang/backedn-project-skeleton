package com.orange.eduback.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orange.eduback.dto.LoginDto;
import com.orange.eduback.dto.LoginResponseDto;
import com.orange.eduback.dto.RegisterDto;
import com.orange.eduback.dto.UserInfoDto;

import java.util.List;

public interface UserService {
    UserInfoDto getUserByUsername(String username);

    void register(RegisterDto registerDto);

    LoginResponseDto login(LoginDto loginDto);

    IPage<UserInfoDto> searchUser(Integer pageNum, Integer pageSize, String username, String email, String phone);

    void updateUser(Long id, UserInfoDto userInfoDto);

    void deleteUser(Long id);

    void deleteBatch(List<Long> ids);
}
