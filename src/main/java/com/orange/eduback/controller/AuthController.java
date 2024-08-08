package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.dto.LoginDto;
import com.orange.eduback.dto.LoginResponseDto;
import com.orange.eduback.dto.RegisterDto;
import com.orange.eduback.service.UserService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public PlainResult<String> register(@RequestBody RegisterDto registerDto) {
        log.info("********register信息:{}************", registerDto);
        userService.register(registerDto);
        return PlainResult.success("register success");
    }

    /*/没有使用jwt
    @PostMapping("/login")
    public PlainResult<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        log.info("********login信息:{}************", loginDto);
        LoginResponseDto loginResponseDto = userService.login(loginDto);
        if (loginResponseDto == null) {
            return PlainResult.fail(408,"用户名或密码错误！");
        }
        return PlainResult.success(loginResponseDto);
    }*/
    //使用jwt
    @PostMapping("/login")
    public PlainResult<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        log.info("********jwt login信息:{}************", loginDto);
        LoginResponseDto loginResponseDto = userService.login(loginDto);
        if (loginResponseDto == null) {
            return PlainResult.fail(408,"用户名或密码错误！");
        }
        return PlainResult.success(loginResponseDto);
    }
}
