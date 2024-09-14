create table order_info
(
    order_id         varchar(32)                         not null
        primary key,
    order_type       int                                 not null comment '订单类型',
    product_id       varchar(32)                         not null comment '商品id',
    transaction_id   varchar(100)                        null comment '支付id,第三方支付生成的id',
    order_desc       varchar(200)                        null comment '订单描述',
    order_status     int       default 0                 not null comment '订单状态（0-未支付，1-已支付，2-支付中，3-支付失败，4-已退款）',
    app_id           varchar(32)                         null comment '订单所属应用/公众号appid',
    mch_id           varchar(32)                         null comment '订单所属商户id',
    order_amount     int                                 not null comment '订单金额',
    currency         varchar(10)                         not null comment '所付金额的币种（人民币-CNY)',
    order_expiration timestamp                           null comment '订单过期时间',
    create_at        timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_at        timestamp default CURRENT_TIMESTAMP null comment '更新时间'
)
    comment '订单表';


