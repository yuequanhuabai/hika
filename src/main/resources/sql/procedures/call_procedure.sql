call sp_insert_student(1000000);

select * from student;

select count(*) from student;

truncate table student;


call sp_insert_teacher(100);


call sp_insert_teacher1(100);


select * from teacher;

truncate table teacher;