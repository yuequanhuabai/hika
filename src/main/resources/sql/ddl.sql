## student
create table student (
                         id varchar(64),
                         name varchar(64),
                         s_create_time timestamp,
                         s_update_time timestamp
);

### teacher
create table teacher (
                         id bigint primary key,
                         name varchar(64),
                         students json,
                         t_create_time timestamp,
                         t_update_time timestamp
);

### classes

create table classes(
    id varchar(64) primary key ,
    name json ,
    teacher json,
    student json,
    create_time timestamp,
    update_time timestamp
);

#### i18n

CREATE TABLE i18n_message (
                              id varchar(64),
                              code VARCHAR(100) NOT NULL,       -- 如 user.name.notblank
                              locale VARCHAR(10) NOT NULL,      -- 如 zh, zh_CN, en
                              message TEXT NOT NULL             -- 如 名字不能為空
);


