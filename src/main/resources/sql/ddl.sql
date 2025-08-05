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
                              code VARCHAR(100) NOT NULL,       --  user.name.notblank
                              locale VARCHAR(10) NOT NULL,      --  zh, zh_CN, en
                              message TEXT NOT NULL             --  ֲܞ
);


-- School Table
CREATE TABLE school (
    id VARCHAR(64) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    addr VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

