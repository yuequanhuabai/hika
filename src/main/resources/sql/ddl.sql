## student
create table student (
                         id varchar(64),
                         name varchar(64)
);

### teacher
create table teacher (
                         id bigint primary key,
                         name varchar(64),
                         students json
);