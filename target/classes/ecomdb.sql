
create database myecomdb;
use myecomdb;

CREATE TABLE customer (
    cust_id INT PRIMARY KEY AUTO_INCREMENT,
    cust_name VARCHAR(100),
    cust_email VARCHAR(150),
    phone VARCHAR(20),
    cust_city VARCHAR(100),
    cust_salary VARCHAR(50),
    date VARCHAR(30),
    gender VARCHAR(20)
);