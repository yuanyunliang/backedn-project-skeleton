create table article
(
    id             bigint auto_increment
        primary key,
    course_name    varchar(100)                        not null,
    course_desc    varchar(200)                        null,
    course_type    varchar(50)                         null,
    course_class   varchar(50)                         null,
    course_price   decimal(10, 2)                      null,
    course_cover   blob                                null,
    course_content text                                null,
    created_at     timestamp default CURRENT_TIMESTAMP null,
    updated_at     timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);


