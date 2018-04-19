DROP DATABASE IF EXISTS `mobilebg`;

CREATE DATABASE `mobilebg`;

USE `mobilebg`;

CREATE TABLE `users` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(30) NOT NULL
);

CREATE TABLE `colors` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20)
);

CREATE TABLE `brands` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20)
);

CREATE TABLE `models` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20),
    `brand_id` INT NOT NULL,
    CONSTRAINT FOREIGN KEY (`brand_id`) REFERENCES `brands`(`id`)
);

CREATE TABLE `categories` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30)
);

CREATE TABLE `cities` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30)
);

CREATE TABLE `extra_categories`  (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30)
);

CREATE TABLE `extras`  (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30),
    `extra_category_id` INT NOT NULL,
    CONSTRAINT FOREIGN KEY (`extra_category_id`) REFERENCES `extra_categories`(`id`)
);

CREATE TABLE `cars_extras`  (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `car_id` INT NOT NULL,
    `extra_id` INT NOT NULL,
    CONSTRAINT FOREIGN KEY (`extra_id`) REFERENCES `extras`(`id`)
);

CREATE TABLE `cars` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `brand_id` INT NOT NULL,
    `model_id` INT NOT NULL,
    `condition` ENUM ('NEW', 'USED', 'PARTS') NOT NULL,
    `year` INT NOT NULL,
    `horse_power` INT NOT NULL,
    `price` DECIMAL NOT NULL,
    `category_id` INT NOT NULL,
    `city_id` INT NOT NULL,
    `max_mileage` INT NOT NULL,
    `engine_type` ENUM ('GASOLINE', 'DIESEL', 'ELECTRIC', 'HYBRIDE') NOT NULL,
    `color_id` INT NOT NULL,
    `gearbox` ENUM ('AUTOMATIC', 'MANUAL', 'SEMI_AUTOMATIC') NOT NULL,
    `car_extras_id` INT NOT NULL,
     CONSTRAINT FOREIGN KEY (`model_id`) REFERENCES `models`(`id`),
     CONSTRAINT FOREIGN KEY (`brand_id`) REFERENCES `brands`(`id`),
     CONSTRAINT FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`),
     CONSTRAINT FOREIGN KEY (`city_id`) REFERENCES `cities`(`id`),
     CONSTRAINT FOREIGN KEY (`color_id`) REFERENCES `colors`(`id`),
     CONSTRAINT FOREIGN KEY (`car_extras_id`) REFERENCES `cars_extras`(`id`)
);

ALTER TABLE `cars_extras` ADD CONSTRAINT FOREIGN KEY (`car_id`) REFERENCES `cars`(`id`);
