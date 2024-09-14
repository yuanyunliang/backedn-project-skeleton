create table solution
(
    id               bigint auto_increment
        primary key,
    solution_name    varchar(100)                        not null,
    solution_desc    varchar(500)                        null,
    solution_type    varchar(10)                         null,
    solution_content longtext                            null,
    solution_cover   blob                                null,
    solution_price   decimal(10, 2)                      null,
    author           varchar(20)                         null,
    watch_count      int       default 0                 null,
    like_count       int       default 0                 null,
    fav_count        int       default 0                 null,
    created_at       timestamp default CURRENT_TIMESTAMP null,
    update_at        timestamp default CURRENT_TIMESTAMP null
);


