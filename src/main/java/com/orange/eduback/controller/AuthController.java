package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.dto.*;
import com.orange.eduback.service.UserService;
import com.orange.eduback.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatEnterpriseQrcodeRequest;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import java.io.IOException;
import java.util.Map;

import static com.orange.eduback.common.PlainResult.success;
import static me.zhyd.oauth.enums.scope.AuthProginnScope.email;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public PlainResult<String> register(@RequestBody RegisterDto registerDto) {
        log.info("********register信息:{}************", registerDto);
        userService.register(registerDto);
        return success("register success");
    }

    //使用jwt
    @PostMapping("/login")
    public PlainResult<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        log.info("********jwt login信息:{}************", loginDto);
        LoginResponseDto loginResponseDto = userService.login(loginDto);
        if (loginResponseDto == null) {
            return PlainResult.fail(408,"用户名或密码错误！");
        }
        return success(loginResponseDto);
    }

    //生成邮箱验证码
    @PostMapping("/mailcode")
    public PlainResult<String> mailCode(@RequestBody EmailDto emailDto) {
        String email = emailDto.getEmail();
        log.info("********to email 信息:{}************", email);
        userService.mailCode(email);
        return success("success");
    }
    //使用邮箱验证码登录
    @PostMapping("/maillogin")
    public PlainResult<LoginResponseDto> mailLogin(@RequestBody MailLoginDto loginDto) {
        log.info("********mail login信息:{}************", loginDto);
        LoginResponseDto loginResponseDto = userService.mailLogin(loginDto);
        if (loginResponseDto == null) {
            return PlainResult.fail(408,"邮箱或验证码错误！");
        }
        return success(loginResponseDto);
    }

    //使用justAuth进行第三方登录实现,获取授权链接并跳转到第三方平台授权页面
    @RequestMapping("/render/{source}")
    public PlainResult<Map<String, String>> renderAuth(@PathVariable String source, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        AuthRequest authRequest = getAuthRequest(source);
        String token = AuthStateUtils.createState();
        //生成gitee的授权url
        String authorizeUrl = authRequest.authorize(token);
        //将这个url返回给前端Vue
        //由Vue去执行 授权页
        Map<String, String> map = new HashMap<>();
        map.put("url", authorizeUrl);
        System.out.println(authorizeUrl);
        return PlainResult.success(map);
    }

    @RequestMapping("/callback/{source}")
    public void thirdPartyLogin(@PathVariable String source, AuthCallback callback,HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse authResponse = authRequest.login(callback);
        AuthUser authUser = (AuthUser) authResponse.getData();
        //此处可以获取到gitee传输过来的用户信息
        //前后端分离 都是通过jwt token来判断当前用户是否有权限访问
        //根据第三方平台的用户信息生成jwt token
        //生成JWT
        String jwtToken = jwtUtils.generateToken(authUser.getUsername());
        String loginResponseDtoJson = userService.login(authUser);
        response.setCharacterEncoding("utf-8");
        //设置当请求头中
        response.setHeader("token",jwtToken);
        String encodedResponseInfo = URLEncoder.encode(loginResponseDtoJson, StandardCharsets.UTF_8.toString());
        //跳转到中转页面
        //这里的地址对应 vue项目中的 中转页面
        //vue的中转页面 会将token从url中取出 记录到本地
        response.sendRedirect("http://localhost:5173/transfer?responseInfo="+encodedResponseInfo);

    }

    private AuthRequest getAuthRequest(String source) {
        switch (source) {
            case "gitee":
                return new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("02f1027c0ccde7711e73fc6e7e8c28bf05036dd359f84ec08ed0528f792cdc7d")
                        .clientSecret("50a4796b76fbfc3e204edc477b9db6afc77bc3c92020a050e910d8548ce2b3e6")
                        .redirectUri("http://127.0.0.1:8080/auth/callback/gitee")
                        .build());
            case "weixin":
                return new AuthWeChatOpenRequest(AuthConfig.builder()
                        .clientId("wxf7967f4421a947f1")
                        .clientSecret("79e8dca94718c2508bcc617d5f67c3ce")
                        .redirectUri("http://127.0.0.1:8080/auth/callback/weixin")
                        .build());
            default:
                return null;

        }

    }


}
