DELIMITER //

CREATE PROCEDURE InsertSchoolData(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_name VARCHAR(255);
    DECLARE random_addr VARCHAR(255);
    DECLARE uuid_str VARCHAR(64);

    WHILE i < num_records DO
            -- Generate UUID
            SET uuid_str = UUID();

            -- Generate random name (10-20 characters)
            SET random_name = (
                SELECT SUBSTRING(MD5(RAND()), 1, FLOOR(10 + (RAND() * 11)))
            );

            -- Generate random address (20-50 characters)
            SET random_addr = (
                SELECT SUBSTRING(MD5(RAND()), 1, FLOOR(20 + (RAND() * 31)))
            );

            -- Insert record
            INSERT INTO school (id, name, addr, create_time)
            VALUES (
                       uuid_str,
                       random_name,
                       random_addr,
                       CURRENT_TIMESTAMP
                   );

            SET i = i + 1;
        END WHILE;
END //

DELIMITER ;