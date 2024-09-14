package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.eduback.domain.OrderInfo;
import com.orange.eduback.service.OrderService;
import com.orange.eduback.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【order(订单表)】的数据库操作Service实现
* @createDate 2024-09-14 14:15:52
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo>
    implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int saveOrder(OrderInfo prepayOrder) {
        return orderMapper.insert(prepayOrder);
    }

    @Override
    public void updateOrder(OrderInfo order) {
        orderMapper.updateById(order);
    }
}




