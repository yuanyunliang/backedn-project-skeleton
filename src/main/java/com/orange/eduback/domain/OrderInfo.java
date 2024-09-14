package com.orange.eduback.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName order
 */
@TableName(value ="order_info")
@Data
public class OrderInfo implements Serializable {
    @TableId(value = "order_id")
    private String orderId;

    private Integer orderType;

    private String productId;

    private String transactionId;

    private String orderDesc;

    private Integer orderStatus;

    private String appId;

    private String mchId;

    private Integer orderAmount;

    private String currency;

    private Date orderExpiration;

    private Date createAt;

    private Date updateAt;

    private static final long serialVersionUID = 1L;
}