CREATE TABLE IF NOT EXISTS emp_info (
                                        id INT PRIMARY KEY,
                                        emp_name VARCHAR(50) NOT NULL,
    address VARCHAR(200),
    emp_age INT,
    password VARCHAR(100),
    phone_number VARCHAR(20),
    emp_mail VARCHAR(50),
    emp_be BOOLEAN,
    entrytime DATE,
    birth DATE
    );
