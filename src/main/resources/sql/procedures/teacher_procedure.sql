DELIMITER //

-- Function to generate simple Snowflake-like ID (for MySQL compatibility)
CREATE FUNCTION generate_snowflake_id()
    RETURNS BIGINT
    DETERMINISTIC
BEGIN
    DECLARE epoch BIGINT DEFAULT 1288834974657; -- Twitter Snowflake epoch (2010-11-04)
    DECLARE timestamp BIGINT;
    DECLARE sequence INT DEFAULT 0;

    SET timestamp = UNIX_TIMESTAMP(CURRENT_TIMESTAMP) * 1000;
    SET sequence = FLOOR(RAND() * 4096); -- 12-bit sequence

    RETURN (timestamp - epoch) << 22 | (FLOOR(RAND() * 1024) << 12) | sequence;
END //

CREATE PROCEDURE sp_insert_teacher(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_str VARCHAR(6);
    DECLARE students_json JSON;

    -- Get 10 student records as JSON array
    SET students_json = (SELECT JSON_ARRAYAGG(
                                        JSON_OBJECT(
                                                'id', id,
                                                'name', name,
                                                's_create_time', s_create_time,
                                                's_update_time', s_update_time
                                        )
                                )
                         FROM student
                         LIMIT 10);

    WHILE i < num_records
        DO
            -- Generate 6 random characters (alphanumeric)
            SET random_str = SUBSTRING(MD5(RAND()), 1, 6);

            INSERT INTO teacher (id,
                                 name,
                                 students,
                                 t_create_time,
                                 t_update_time)
            VALUES (generate_snowflake_id(),
                    CONCAT('teacher_', random_str),
                    students_json,
                    CURRENT_TIMESTAMP,
                    CURRENT_TIMESTAMP);

            SET i = i + 1;
        END WHILE;
END //

DELIMITER ;