
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
