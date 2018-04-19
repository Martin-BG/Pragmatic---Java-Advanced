DROP DATABASE IF EXISTS `pragmatic`;

CREATE DATABASE `pragmatic`;

USE `pragmatic`;

CREATE TABLE `students`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `age` INT NOT NULL,
    `neighbourhood` VARCHAR(50) NOT NULL,
    `nationality` VARCHAR(40) NOT NULL,
    `specialty` VARCHAR(40) NOT NULL
);

CREATE TABLE `employees`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `age` INT NOT NULL,
    `department` VARCHAR(50) NOT NULL,
    `salary` DOUBLE NOT NULL
);
