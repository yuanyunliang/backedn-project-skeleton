package com.orange.eduback.service;

import com.orange.eduback.domain.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【order_info(订单表)】的数据库操作Service
* @createDate 2024-09-14 14:15:52
*/
public interface OrderService extends IService<OrderInfo> {

    int saveOrder(OrderInfo prepayOrder);

    void updateOrder(OrderInfo order);
}
