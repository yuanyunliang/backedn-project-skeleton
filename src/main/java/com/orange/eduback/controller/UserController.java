package com.orange.eduback.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orange.eduback.common.PlainResult;
import com.orange.eduback.dto.UserInfoDto;
import com.orange.eduback.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

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
