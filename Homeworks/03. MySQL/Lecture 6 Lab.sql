DROP DATABASE IF EXISTS `law_enforcer`;

CREATE DATABASE `law_enforcer`;

USE `law_enforcer`;


CREATE TABLE `judge` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `age` TINYINT UNSIGNED NOT NULL,
    `salary` DECIMAL(10 , 2 ) NOT NULL
);


CREATE TABLE `prison` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    `address` VARCHAR(50) NOT NULL,
    `capacity` MEDIUMINT UNSIGNED NOT NULL
);


CREATE TABLE `guard` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `age` TINYINT UNSIGNED NOT NULL,
    `years_of_service` TINYINT UNSIGNED NOT NULL,
    `salary` DECIMAL(10 , 2 ) NOT NULL,
    `prison_id` INT UNSIGNED NOT NULL,
    CONSTRAINT `fk_guard_prison` FOREIGN KEY (`prison_id`)
        REFERENCES `prison` (`id`)
);


CREATE TABLE `criminal` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(20) NOT NULL,
    `middle_name` VARCHAR(20),
    `last_name` VARCHAR(20) NOT NULL,
    `personal_id` VARCHAR(20) NOT NULL UNIQUE,
    `age` TINYINT UNSIGNED NOT NULL,
    `prison_id` INT UNSIGNED NOT NULL,
    CONSTRAINT `fk_criminal_prison` FOREIGN KEY (`prison_id`)
        REFERENCES `prison` (`id`)
);


CREATE TABLE `crime` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(20) NOT NULL,
    `penalty_years` TINYINT UNSIGNED NOT NULL,
    `criminal_id` INT UNSIGNED NOT NULL,
    `judge_id` INT UNSIGNED NOT NULL,
    CONSTRAINT `fk_crime_criminal` FOREIGN KEY (`criminal_id`)
        REFERENCES `criminal` (`id`),
    CONSTRAINT `fk_crime_judge` FOREIGN KEY (`judge_id`)
        REFERENCES `judge` (`id`)
);