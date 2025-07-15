DELIMITER //

CREATE PROCEDURE InitializeClasses(IN record_count INT)
BEGIN
    -- Declare all variables at the beginning
    DECLARE i INT DEFAULT 1;
    DECLARE teacher_json JSON;
    DECLARE student_json JSON;
    DECLARE name_json JSON;

    WHILE i <= record_count DO
            -- Fetch 10 teachers as JSON
            SET teacher_json = (
                SELECT
                               JSON_OBJECT(
                                       'id', id,
                                       'name', name,
                                       'students', students,
                                       'tCreateTime', t_create_time,
                                       'tUpdateTime', t_update_time
                               )

                FROM teacher
                LIMIT 1
            );

            -- Fetch 10 students as JSON
            SET student_json = (
                SELECT
                               JSON_OBJECT(
                                       'id', id,
                                       'name', name,
                                       'sCreateTime', s_create_time,
                                       'sUpdateTime', s_update_time
                               )

                FROM student
                LIMIT 1
            );

            -- Generate name JSON based on Name POJO
            SET name_json = JSON_OBJECT(
                    'cn', CONCAT('班级', i),
                    'en', CONCAT('Class ', i),
                    'hk', CONCAT('班級', i)
                            );

            -- Insert into classes table
            INSERT INTO classes (id, name, teacher, student, create_time, update_time)
            VALUES (
                       UUID(), -- Generate unique ID
                       name_json,
                       teacher_json,
                       student_json,
                       CURRENT_TIMESTAMP,
                       CURRENT_TIMESTAMP
                   );

            SET i = i + 1;
        END WHILE;
END //

DELIMITER ;