DROP DATABASE IF EXISTS `car_store`;

CREATE DATABASE `car_store`;

USE `car_store`;

CREATE TABLE `additional_feature` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `gas_system` BIT(1),
    `air_conditioner` BIT(1),
    `climatronic` BIT(1),
    `seats_heater` BIT(1),
    `electric_windows` BIT(1)
);

CREATE TABLE `car` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `color` VARCHAR(20) NOT NULL,
    `model` VARCHAR(20) NOT NULL,
    `producer` VARCHAR(20) NOT NULL,
    `year_produced` YEAR(4) NOT NULL,
    `horse_power` INT(4) UNSIGNED NOT NULL,
    `engine_volume` INT(5) UNSIGNED NOT NULL,
    `additional_feature_id` INT UNSIGNED,
    CONSTRAINT `fk_car_additional_feature` FOREIGN KEY (`additional_feature_id`)
        REFERENCES `additional_feature` (`id`)
);

CREATE TABLE `address` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `city` VARCHAR(30) NOT NULL,
    `distinct` VARCHAR(30) NOT NULL,
    `street` VARCHAR(30),
    `number` SMALLINT UNSIGNED
);

CREATE TABLE `user` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    `password` VARCHAR(20) NOT NULL,
    `email` VARCHAR(30) NOT NULL UNIQUE,
    `age` TINYINT UNSIGNED NOT NULL,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `sex` ENUM('male', 'female') NOT NULL,
    `address_id` INT UNSIGNED NOT NULL,
    CONSTRAINT `fk_user_address` FOREIGN KEY (`address_id`)
        REFERENCES `address` (`id`)
);

CREATE TABLE `ad` (
    `user_id` INT UNSIGNED NOT NULL,
    `car_id` INT UNSIGNED NOT NULL UNIQUE,
    `desc` TEXT NOT NULL,
    `date` DATE NOT NULL,
    CONSTRAINT `fk_ad_user` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`),
    CONSTRAINT `fk_ad_car` FOREIGN KEY (`car_id`)
        REFERENCES `car` (`id`),
    CONSTRAINT `pk_ad_user_car` PRIMARY KEY (`user_id` , `car_id`)
);

INSERT INTO `address` 
        (`city`, `distinct`, `street`, `number`) 
    VALUES 
        ('Sofia', 'Mladost-4', NULL, NULL),
        ('Varna', 'No Idea', NULL, NULL),
        ('Sofia', 'Centre', 'Vitosha blv.', '1');
        
INSERT INTO `user` 
        (`name`, `password`, `email`, `age`,`first_name`,`last_name`, `sex`, `address_id`)
    VALUES
        ('chocho', 'taino obicham Azis', 'chocho@home.eu', 29, 'Peter', 'Petrov', 'male', 2),
        ('macho', 'az sum the best', 'macho@world.com', 18, 'Ivan', 'Ivanov', 'male', 1),
        ('ani69', 'nokoi ne me razbira', 'anito@out.bz', 23, 'Ani', 'Aneva', 'female', 3);

INSERT INTO `additional_feature` 
        (`gas_system`, `air_conditioner`, `climatronic`, `seats_heater`, `electric_windows`)
    VALUES
        (1, 0, 1, 0, 0),
        (1, 1, 0, 1, 1),
        (0, 0, 0, 0, 0);

INSERT INTO `car`
        (`color`, `model`, `producer`, `year_produced`, 
        `horse_power`, `engine_volume`, `additional_feature_id`)
    VALUES
        ('Black', 'Model 1', 'BMW', 1983, 90, 1500, 3),
        ('White', 'Model 2', 'BMW', 1988, 60, 1200, 1),
        ('Red', 'Model 3', 'Trabant', 1993, 70, 1400, 2);

INSERT INTO `ad` 
        (`user_id`, `car_id`, `desc`, `date`) 
    VALUES
        (1, 3, 'A great car for cheap', DATE(NOW())),
        (2, 2, 'Another great car for cheap', '2018-03-01'),
        (3, 1, 'Roars like a tiger', DATE(NOW()));


-- Join all tables on ad table entries
SELECT 
    *
FROM
    `ad` AS a
        JOIN
    `user` AS u ON a.user_id = u.id
        JOIN
    `car` AS c ON a.car_id = c.id
        JOIN
    `address` AS addr ON u.address_id = addr.id
        JOIN
    `additional_feature` AS af ON c.additional_feature_id = af.id
ORDER BY u.id , c.id
LIMIT 5;


-- Show only ads for cars with electric windows
SELECT 
    *
FROM
    `ad` AS a
        JOIN
    `user` AS u ON a.user_id = u.id
        JOIN
    `car` AS c ON a.car_id = c.id
        JOIN
    `address` AS addr ON u.address_id = addr.id
        JOIN
    `additional_feature` AS af ON c.additional_feature_id = af.id
WHERE
    af.electric_windows = TRUE
ORDER BY u.id , c.id
LIMIT 5;


-- Show only ads from Sofia
SELECT 
    *
FROM
    `ad` AS a
        JOIN
    `user` AS u ON a.user_id = u.id
        JOIN
    `car` AS c ON a.car_id = c.id
        JOIN
    `address` AS addr ON u.address_id = addr.id
        JOIN
    `additional_feature` AS af ON c.additional_feature_id = af.id
WHERE
    addr.city = 'Sofia'
ORDER BY u.id , c.id
LIMIT 5;