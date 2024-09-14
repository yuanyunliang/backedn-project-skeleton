create table user
(
    id            bigint auto_increment
        primary key,
    user_name     varchar(100)                        not null,
    user_email    varchar(100)                        not null,
    user_password varchar(100)                        not null,
    user_phone    varchar(20)                         null,
    user_address  varchar(100)                        null,
    user_role     varchar(50)                         null,
    user_status   varchar(50)                         null,
    created_at    timestamp default CURRENT_TIMESTAMP null,
    updated_at    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    collate = utf8mb4_unicode_ci;


