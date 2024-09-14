create table course
(
    id                 bigint auto_increment
        primary key,
    course_name        varchar(100)                        not null,
    course_description varchar(200)                        null,
    course_type        varchar(50)                         null,
    course_duration    varchar(50)                         null,
    course_fee         decimal(10, 2)                      null,
    course_url         varchar(200)                        null,
    course_status      varchar(50)                         null,
    created_at         timestamp default CURRENT_TIMESTAMP null,
    updated_at         timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    collate = utf8mb4_unicode_ci;


