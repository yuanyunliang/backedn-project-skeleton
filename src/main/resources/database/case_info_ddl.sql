create table case_info
(
    id           bigint auto_increment
        primary key,
    case_name    varchar(100)              null,
    case_desc    varchar(500)              null,
    case_type    varchar(10)               null,
    case_content longtext                  null,
    case_cover   blob                      null,
    case_price   decimal(10, 2)            null,
    author       varchar(20)               null,
    watch_count  int       default 0       null,
    like_count   int       default 0       null,
    fav_count    int       default 0       null,
    created_at   timestamp default (now()) null,
    update_at    timestamp default (now()) null
)
    comment '案例';


