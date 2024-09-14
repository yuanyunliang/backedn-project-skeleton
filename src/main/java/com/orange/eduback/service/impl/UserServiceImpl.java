package com.orange.eduback.service.impl;

import org.slf4j.Logger;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.eduback.common.EduBaseException;
import com.orange.eduback.domain.MailCode;
import com.orange.eduback.domain.User;
import com.orange.eduback.dto.*;
import com.orange.eduback.mapper.UserMapper;
import com.orange.eduback.service.UserService;
import com.orange.eduback.util.*;
import jakarta.annotation.Resource;
import me.zhyd.oauth.model.AuthUser;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private JavaMailSender mailSender;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserInfoDto getUserByUsername(String username) {
        User user = userMapper.findOneByUsername(username);
        if(user==null){
            return null;
        }
        return toUerInfoDto(user);
    }

    @Override
    public void register(RegisterDto registerDto) {
        User originUser = userMapper.findOneByUsername(registerDto.getUsername());
        if(originUser!=null){
            throw new EduBaseException(405,"用户名已存在！");
        }
        if(!UserCheckUitls.checkRegister(registerDto)){
            throw new EduBaseException(406,"注册信息有误，请重新填写！");
        }
        User user = new User();
        user.setUserName(registerDto.getUsername());
        //user.setUserPassword(registerDto.getPassword());
        //加密存储用户的密码
        user.setUserPassword(EncryptUtils.md5(registerDto.getPassword()));
        user.setUserEmail(registerDto.getEmail());
        user.setUserPhone(registerDto.getPhone());
        user.setUserAddress(registerDto.getAddress());
        user.setUserRole(registerDto.getRole());
        userMapper.insert(user);
    }

    //使用jwt
    public LoginResponseDto login(LoginDto loginDto) {
        User user = userMapper.findOneByUsername(loginDto.getUsername());
        if(user==null){
            return null;
        }
        if(!user.getUserPassword().equals(EncryptUtils.md5(loginDto.getPassword()))){
            return null;
        }
        return toLoginResponseDto(user);
    }

    //第三方登录
    public String login(AuthUser authUser) {
        return toJson(authUser);
    }

    @Override
    public IPage<UserInfoDto> searchUser(Integer pageNum, Integer pageSize, String username, String email, String phone) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getUpdatedAt);
        if (username !=null){
            queryWrapper.like(User::getUserName,username);
        }
        if (email !=null){
            queryWrapper.like(User::getUserEmail,email);
        }
        if (phone !=null){
            queryWrapper.like(User::getUserPhone,phone);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        if(userPage==null){
            return null;
        }
        return userPage.convert(this::toUerInfoDto);
    }

    @Override
    public void updateUser(Long id, UserInfoDto userInfoDto) {
        User user = userMapper.selectById(id);
        if(user==null){
            throw new EduBaseException(400,"用户不存在！");
        }
        user.setUserName(userInfoDto.getUsername());
        user.setUserEmail(userInfoDto.getEmail());
        user.setUserPhone(userInfoDto.getPhone());
        user.setUserAddress(userInfoDto.getAddress());
        user.setUserRole(userInfoDto.getRole());
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        userMapper.deleteBatchIds(ids);
    }

    @Override
    public LoginResponseDto mailLogin(MailLoginDto loginDto) {
        //校验邮箱及验证码
        JedisUtils jedisUtils = JedisUtils.INSTANCE;
        String mailcode = jedisUtils.get(loginDto.getMail());
        if(mailcode==null){
            throw new EduBaseException(407,"验证码已过期！");
        }
        if(!mailcode.equals(loginDto.getVerifyCode())){
            throw new EduBaseException(407,"验证码错误！");
        }
        User user = userMapper.findOneByMail(loginDto.getMail());
        if(user==null){
            throw new EduBaseException(407,"用户不存在！");
        }
        return toLoginResponseDto(user);
    }

    @Override
    public void mailCode(String mail) {
        String mailcode = MailUtils.generateMailCode();
        //发送邮件
        MailUtils.sendMail(mailSender,mail,mailcode);
        //保存到redis
        JedisUtils jedisUtils = JedisUtils.INSTANCE;
        jedisUtils.setnxex(mail,mailcode,300);
        log.info("********redis中key为mail：{}的值为{}************", mail, jedisUtils.get(mail));
    }

    @Override
    public LoginResponseDto updateRole(String username, String role) {
        User user = userMapper.findOneByUsername(username);
        if(user==null){
            throw new EduBaseException(400,"用户不存在！");
        }
        user.setUserRole(role);
        userMapper.updateById(user);
        return toLoginResponseDto(user);
    }

    //使用jwt
    private LoginResponseDto toLoginResponseDto(User user) {
        //String token = jwtUtils.generateToken(user.getUserName(),String.valueOf(user.getId()),user.getUserRole()); ;
        String token = jwtUtils.generateToken(user.getUserName());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setUsername(user.getUserName());
        loginResponseDto.setRole(user.getUserRole());
        return loginResponseDto;
    }

    //第三方登录
    private String toJson(AuthUser authUser) {
        String token = jwtUtils.generateToken(authUser.getUsername());
        ThirdPartyLoginResponseDto loginResponseDto = new ThirdPartyLoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setUsername(authUser.getUsername());
        loginResponseDto.setAvatar(authUser.getAvatar());
        return JSON.toJSONString(loginResponseDto);
    }

    private UserInfoDto toUerInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUserName());
        userInfoDto.setEmail(user.getUserEmail());
        userInfoDto.setPhone(user.getUserPhone());
        userInfoDto.setAddress(user.getUserAddress());
        userInfoDto.setRole(user.getUserRole());
        return userInfoDto;
    }
}
