
####

select * from student;
select  count(*) from student;

drop table student;

truncate table student;

call sp_insert_student(20);

####

select count(*) from teacher;
select * from  teacher;

truncate table teacher;

call sp_insert_teacher(20);



####

select * from classes;

call InitializeClasses(20);

drop procedure InitializeClasses;


show create procedure sp_insert_teacher1;

show create procedure sp_insert_teacher;


select * from i18n_message;

insert into i18n_message(id,locale,code,message) values (uuid(),'hk','user.id','·±ów');

insert into i18n_message(id,locale,code,message) values (uuid(),'cn','user.id','ÖÐÎÄ');

insert into i18n_message(id,locale,code,message) values (uuid(),'en','user.id','Ó¢ÎÄ');

delete  from i18n_message where id='80aa6186-6d56-11f0-9a5b-2cf05d7f891d'




-- School Table
CREATE TABLE school (
                        id varchar(64) PRIMARY KEY ,
                        name VARCHAR(255) NOT NULL,
                        addr VARCHAR(255),
                        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

drop table school;

select * from school;

call InsertSchoolData(10);

show create table school;

call InsertSchoolData(10)


select * from school;




