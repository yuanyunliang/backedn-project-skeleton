package com.orange.eduback.util;

public class PayUtils {

    //生成带字母和数字的16位随机字符串的VIP会员交易订单号，以v字母开头,应为全部小写
    public static String generateVipOrderNo() {
        String orderNo = "v";
        for (int i = 0; i < 31; i++) {
            int random = (int) (Math.random() * 62);
            if (random < 10) {
                orderNo += random;
            } else if (random < 36) {
                orderNo += (char) (random + 55);
            } else {
                orderNo += (char) (random + 61);
            }
        }
        return orderNo;
    }

}
