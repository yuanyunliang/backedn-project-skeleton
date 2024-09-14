package com.orange.eduback.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orange.eduback.domain.User;
import com.orange.eduback.dto.*;
import me.zhyd.oauth.model.AuthUser;

import java.util.List;

public interface UserService {
    UserInfoDto getUserByUsername(String username);

    void register(RegisterDto registerDto);

    LoginResponseDto login(LoginDto loginDto);

    String login(AuthUser authUser);

    IPage<UserInfoDto> searchUser(Integer pageNum, Integer pageSize, String username, String email, String phone);

    void updateUser(Long id, UserInfoDto userInfoDto);

    void deleteUser(Long id);

    void deleteBatch(List<Long> ids);

    LoginResponseDto mailLogin(MailLoginDto loginDto);

    void mailCode(String mail);

    LoginResponseDto updateRole(String username, String role);
}
