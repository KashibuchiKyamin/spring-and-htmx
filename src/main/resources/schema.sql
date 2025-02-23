DROP TABLE IF EXISTS order_request_status;
CREATE TABLE order_request_status (
    ulid CHAR(26) PRIMARY KEY,
    order_number CHAR(10),
    branch_number CHAR(2),
    reception_number CHAR(10),
    correction_number CHAR(2),
    project_name VARCHAR(255),
    customer_name VARCHAR(255),
    submission_date DATE,
    delivery_date DATE,
    status CHAR(2)
);
