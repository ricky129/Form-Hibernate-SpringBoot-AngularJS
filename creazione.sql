CREATE DATABASE miodb;
USE miodb;
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name char(20),
    email varchar(50),
    password varchar(255)
    );