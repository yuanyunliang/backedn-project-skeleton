package com.orange.eduback.mapper;

import com.orange.eduback.domain.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【order(订单表)】的数据库操作Mapper
* @createDate 2024-09-14 14:15:52
* @Entity com.orange.eduback.domain.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo> {

    @Insert("INSERT INTO `order_info` (order_id, order_type, product_id,order_amount, app_id, mch_id, order_desc, order_status, currency) VALUES (#{orderId}, #{orderType}, #{productId},#{orderAmount},#{appId}, #{mchId}, #{orderDesc}, #{orderStatus}, #{currency})")
    int insert(OrderInfo order);
}




