package com.orange.eduback.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orange.eduback.common.PlainResult;
import com.orange.eduback.domain.User;
import com.orange.eduback.dto.LoginResponseDto;
import com.orange.eduback.dto.UserInfoDto;
import com.orange.eduback.service.UserService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @GetMapping("/info")
    public PlainResult<UserInfoDto> getUserInfo(@RequestParam String username) {
        return PlainResult.success(userService.getUserByUsername(username));
    }
    @GetMapping("/search")
    public PlainResult<IPage<UserInfoDto>> searchUser(@RequestParam Integer pageNum,
                                                      @RequestParam Integer pageSize,
                                                      @RequestParam String username,
                                                      @RequestParam String email,
                                                      @RequestParam String phone) {
        IPage<UserInfoDto> userInfoDtoIPage =userService.searchUser(pageNum, pageSize, username, email, phone);
        return PlainResult.success(userInfoDtoIPage);
    }

    @PutMapping("/update/{id}")
    public PlainResult<String> updateUser(@PathVariable Long id, @RequestBody UserInfoDto userInfoDto) {
        userService.updateUser(id, userInfoDto);
        return PlainResult.success("success");
    }

    //更新用户角色
    @PostMapping("/updateRole")
    public PlainResult<LoginResponseDto> updateRole(@RequestBody Map<String,String> reqMap) {
        String username = reqMap.get("username");
        String role = reqMap.get("role");
        log.info("update role by username:{},role: {}",username,role);
        return PlainResult.success(userService.updateRole(username,role));
    }

    @DeleteMapping("/delete/{id}")
    public PlainResult<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return PlainResult.success("success");
    }

    @PostMapping("/deleteBatch")
    public PlainResult<String> deleteBatch(@RequestBody List<Long> ids) {
        userService.deleteBatch(ids);
        return PlainResult.success("success");
    }
}
