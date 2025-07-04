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