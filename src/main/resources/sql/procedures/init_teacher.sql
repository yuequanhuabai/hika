DELIMITER //

CREATE PROCEDURE CreateTeacherWithStudents(
    IN p_record_count INT
)
BEGIN
    DECLARE v_students_json JSON;
    DECLARE v_counter INT DEFAULT 0;
    DECLARE v_teacher_id BIGINT;
    DECLARE v_teacher_name VARCHAR(64);

    -- 從student表獲取所有學生數據並轉為JSON數組
    SELECT JSON_ARRAYAGG(
                   JSON_OBJECT(
                           'id', id,
                           'name', name
                   )
           ) INTO v_students_json
    FROM student;
    -- 循環插入指定數量的教師記錄
    WHILE v_counter < p_record_count DO
            -- 生成雪花ID（使用UUID並轉為大整數）
            SET v_teacher_id = ABS(CRC32(UUID()));
            -- 生成教師名稱：teacher + 隨機字符串
            SET v_teacher_name = CONCAT('teacher_', SUBSTRING(MD5(RAND()), 1, 8));
            -- 插入teacher表
            INSERT INTO teacher (id, name, students)
            VALUES (v_teacher_id, v_teacher_name, v_students_json);

            SET v_counter = v_counter + 1;
        END WHILE;
END //

DELIMITER ;