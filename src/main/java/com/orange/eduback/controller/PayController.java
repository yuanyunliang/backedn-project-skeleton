package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.domain.OrderInfo;
import com.orange.eduback.dto.PayResponseDto;
import com.orange.eduback.service.OrderService;
import com.orange.eduback.service.UserService;
import com.orange.eduback.util.PayUtils;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(PayController.class);
    /** 商户号 */
    private static String merchantId = "1685150497";
    /** 商户API私钥路径 */
    private static String privateKeyPath;
    /** 商户证书序列号 */
    private static String merchantSerialNumber = "22750686E6365B5894E0BD6A8CA41DBFC66C5C08";
    /**appid公众号ID*/
    private static String appId = "wx324970db769e67d7";
    /** 商户APIV3密钥 */
    private static String apiV3Key = "WkblPyuCsTasOBb9DUxOW3gkHdjDGtot";
    /**通知回调地址*/
    private static String notifyUrl = "https://www.qianshuedu.cn/pay/wechatpay/notify";
    /**全局静态config对象*/
    private static Config config;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:apiclient_key.pem");
        privateKeyPath = resource.getFile().getAbsolutePath();
        //使用自动更新平台证书的RSA配置
        config =new RSAAutoCertificateConfig.Builder()
                .merchantId(merchantId)
                .merchantSerialNumber(merchantSerialNumber)
                .privateKeyFromPath(privateKeyPath)
                .apiV3Key(apiV3Key)
                .build();
    }

    //微信支付
    @PostMapping("/wechatpay")
    public PlainResult<PayResponseDto> wechatpay(@RequestBody Map<String,String> payRequest) {
        log.info("********wechatpay信息:{}************", payRequest);
        //获取前端传递的参数productId为商品id，amount为金额
        String productId = payRequest.get("productId");
        Integer totalAmount = Integer.valueOf(payRequest.get("amount"));
        //生成订单号
        String outTradeNo = PayUtils.generateVipOrderNo();
        //构建NativePayService
        NativePayService nativePayService = new NativePayService.Builder().config(config).build();
        PrepayRequest prepayRequest = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(totalAmount);
        prepayRequest.setAmount(amount);
        prepayRequest.setAppid(appId);
        prepayRequest.setMchid(merchantId);
        prepayRequest.setDescription("Vip会员费");
        prepayRequest.setNotifyUrl(notifyUrl);
        prepayRequest.setOutTradeNo(outTradeNo);
        PrepayResponse prepayResponse = nativePayService.prepay(prepayRequest);
        log.info("********wechatpay返回信息二维码链接code_url:{}************", prepayResponse.getCodeUrl());
        //保存订单信息到数据库的order表
        OrderInfo prepayOrder = new OrderInfo();
        prepayOrder.setOrderId(outTradeNo);
        prepayOrder.setOrderType(1);//1表示vip会员购买订单
        prepayOrder.setProductId(productId);
        prepayOrder.setOrderAmount(totalAmount);
        prepayOrder.setAppId(appId);
        prepayOrder.setMchId(merchantId);
        prepayOrder.setOrderDesc("Vip会员费");
        prepayOrder.setOrderStatus(0);//0表示未支付
        prepayOrder.setCurrency("CNY");
        int saveResult = orderService.saveOrder(prepayOrder);
        log.info("********订单保存结果:{}************", saveResult);
        if (saveResult == 0) {
            return PlainResult.fail(500,"订单保存失败");
        }
        //返回支付二维码链接和生成的唯一订单号
        PayResponseDto payResponseDto = new PayResponseDto();
        payResponseDto.setCodeUrl(prepayResponse.getCodeUrl());
        payResponseDto.setTradeNo(outTradeNo);
        return PlainResult.success(payResponseDto);
    }
    //微信支付回调地址
    @PostMapping("/wechatpay/notify")
    public PlainResult<String> wechatpayNotify(@RequestBody String data) {
        log.info("********wechatpayNotify信息:{}************", data);
        return PlainResult.success("wechatpayNotify success");
    }
    //根据交易id去微信支付查询订单状态
    @PostMapping("/wechatpay/getTradeStatus")
    public PlainResult<String> getTradeStatus(@RequestBody Map<String,String> payRequest) {
        log.info("********getOrderStatus信息:{}************", payRequest.get("tradeNo"));
        String outTradNo = payRequest.get("tradeNo");
        String username = payRequest.get("username");
        //根据交易id和商户号去微信支付查询订单状态
        //构建NativePayService
        NativePayService nativePayService = new NativePayService.Builder().config(config).build();
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setOutTradeNo(outTradNo);
        request.setMchid(merchantId);
        Transaction transaction = nativePayService.queryOrderByOutTradeNo(request);
        //返回订单状态
        log.info("********getOrderStatus返回信息:{}************", transaction.getTradeState().name());
        //如果订单的状态为SUCCESS表示支付成功,支付成功则更新订单表的订单状态为1，表示已支付，同时更新微信支付的交易id
        if (transaction.getTradeState().name().equals("SUCCESS")) {
            OrderInfo order = new OrderInfo();
            order.setOrderId(outTradNo);
            order.setOrderStatus(1);
            order.setTransactionId(transaction.getTransactionId());
            orderService.updateOrder(order);

            //支付成功后更新用户的vip会员状态
            userService.updateRole(username,"vip");
        }
        return PlainResult.success(transaction.getTradeState().name());
    }
}
