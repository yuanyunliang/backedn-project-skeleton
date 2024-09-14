package com.orange.eduback.util;

import com.orange.eduback.dto.RegisterDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCheckUitls {

    // 定义手机号码的正则表达式
    private static final String PHONE_NUMBER_REGEX = "^1[3-9]\\d{9}$";

    //定义邮箱的正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public static boolean checkRegister(RegisterDto registerDto) {
        if (registerDto.getUsername() == null || registerDto.getUsername().length() < 3 || registerDto.getUsername().length() > 20) {
            return false;
        }
        if (registerDto.getPassword() == null || registerDto.getPassword().length() < 6 || registerDto.getPassword().length() > 20 ) {
            return false;
        }
        if(!isValidMail(registerDto.getEmail())) {
            return false;
        }
        if (!isValidPhoneNumber(registerDto.getPhone())) {
            return false;
        }
        return true;
    }

    // 验证方法
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // 创建一个 Pattern 对象
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);

        // 使用 Matcher 去匹配输入的手机号
        Matcher matcher = pattern.matcher(phoneNumber);

        // 返回是否匹配
        return matcher.matches();
    }

    private static boolean isValidMail(String email) {
        //使用正则表达式判断邮箱
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }


}
