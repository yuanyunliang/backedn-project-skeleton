create table mail_code
(
    id          int auto_increment
        primary key,
    email       varchar(100) not null,
    verify_code varchar(20)  null comment '验证码'
)
    comment '邮箱验证码表';


