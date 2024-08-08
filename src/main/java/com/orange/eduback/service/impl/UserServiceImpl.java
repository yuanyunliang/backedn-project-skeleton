package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.eduback.common.EduBaseException;
import com.orange.eduback.domain.User;
import com.orange.eduback.dto.LoginDto;
import com.orange.eduback.dto.LoginResponseDto;
import com.orange.eduback.dto.RegisterDto;
import com.orange.eduback.dto.UserInfoDto;
import com.orange.eduback.mapper.UserMapper;
import com.orange.eduback.service.UserService;
import com.orange.eduback.util.EncryptUtils;
import com.orange.eduback.util.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtils jwtUtils;

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
            throw new EduBaseException(400,"用户名已存在！");
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

    /*/没有使用jwt
    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        User user = userMapper.findOneByUsername(loginDto.getUsername());
        if(user==null){
            return null;
        }
       // if(!user.getUserPassword().equals(loginDto.getPassword())){
        if(!user.getUserPassword().equals(EncryptUtils.md5(loginDto.getPassword()))){
            return null;
        }
        return toLoginResponseDto(user);
    }*/
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
    /*/没有使用jwt
    private LoginResponseDto toLoginResponseDto(User user) {
        String token = user.getId() + "-" + user.getUserName() + "-" + user.getUserRole();
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        //loginResponseDto.setToken(token);
        loginResponseDto.setUsername(user.getUserName());
        //loginResponseDto.setRole(user.getUserRole());
        return loginResponseDto;
    }*/
    //使用jwt
    private LoginResponseDto toLoginResponseDto(User user) {
        String token = jwtUtils.generateToken(user.getUserName(),String.valueOf(user.getId()),user.getUserRole()); ;
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setUsername(user.getUserName());
        loginResponseDto.setRole(user.getUserRole());
        return loginResponseDto;
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
