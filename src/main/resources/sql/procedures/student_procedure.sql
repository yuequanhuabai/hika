DELIMITER //

CREATE PROCEDURE sp_insert_student(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_str VARCHAR(6);

    WHILE i < num_records DO
            -- Generate 6 random characters (alphanumeric)
            SET random_str = SUBSTRING(MD5(RAND()), 1, 6);

            INSERT INTO student (
                id,
                name,
                s_create_time,
                s_update_time
            ) VALUES (
                         UUID(),
                         CONCAT('student_', random_str),
                         CURRENT_TIMESTAMP,
                         CURRENT_TIMESTAMP
                     );

            SET i = i + 1;
        END WHILE;
END //

DELIMITER ;