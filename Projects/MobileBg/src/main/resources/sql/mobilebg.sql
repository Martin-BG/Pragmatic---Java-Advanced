DROP DATABASE IF EXISTS `mobilebg`;

CREATE DATABASE `mobilebg`;

USE `mobilebg`;

CREATE TABLE `users` (
  `id`       INT PRIMARY KEY AUTO_INCREMENT,
  `email`    VARCHAR(50) UNIQUE NOT NULL,
  `password` VARCHAR(30)        NOT NULL,
  INDEX (`email`(30))
);

CREATE TABLE `colors` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20),
  INDEX (`name`)
);

CREATE TABLE `brands` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20),
  INDEX (`name`)
);

CREATE TABLE `models` (
  `id`       INT PRIMARY KEY AUTO_INCREMENT,
  `name`     VARCHAR(20),
  `brand_id` INT NOT NULL,
  INDEX (`name`),
  CONSTRAINT `fk_models_brands` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
    ON DELETE CASCADE
);

CREATE TABLE `categories` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30),
  INDEX (`name`)
);

CREATE TABLE `cities` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30),
  INDEX (`name`)
);

CREATE TABLE `extras_categories` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30),
  INDEX (`name`)
);

CREATE TABLE `extras` (
  `id`                INT PRIMARY KEY AUTO_INCREMENT,
  `name`              VARCHAR(30),
  `extra_category_id` INT NOT NULL,
  INDEX (`name`),
  CONSTRAINT `fk_extras_extras_categories` FOREIGN KEY (`extra_category_id`) REFERENCES `extras_categories` (`id`)
    ON DELETE CASCADE
);

CREATE TABLE `cars_extras` (
  `car_id`   INT NOT NULL,
  `extra_id` INT NOT NULL,
  PRIMARY KEY (`car_id`, `extra_id`),
  CONSTRAINT `fk_cars_extras_extras` FOREIGN KEY (`extra_id`) REFERENCES `extras` (`id`)
    ON DELETE CASCADE
);

CREATE TABLE `cars` (
  `id`            INT PRIMARY KEY AUTO_INCREMENT,
  `year`          INT                                               NOT NULL,
  `horse_power`   INT                                               NOT NULL,
  `price`         DECIMAL(19, 2)                                    NOT NULL,
  `max_mileage`   INT                                               NOT NULL,
  `condition`     ENUM ('NEW', 'USED', 'PARTS')                     NOT NULL,
  `engine_type`   ENUM ('GASOLINE', 'DIESEL', 'ELECTRIC', 'HYBRID') NOT NULL,
  `gearbox`       ENUM ('AUTOMATIC', 'MANUAL', 'SEMI_AUTOMATIC')    NOT NULL,
  `brand_id`      INT                                               NOT NULL,
  `model_id`      INT                                               NOT NULL,
  `category_id`   INT                                               NOT NULL,
  `color_id`      INT                                               NOT NULL,
  `city_id`       INT                                               NOT NULL,
  INDEX (`price`),
  CONSTRAINT `fk_cars_brands` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_cars_models` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_cars_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_cars_colors` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_cars_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
    ON DELETE CASCADE
);

ALTER TABLE `cars_extras`
  ADD CONSTRAINT `fk_cars_extras_cars` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
  ON DELETE CASCADE;

CREATE TABLE `users_cars` (
  `user_id` INT NOT NULL,
  `car_id`  INT NOT NULL,
  CONSTRAINT `pk_users_cars` PRIMARY KEY (`user_id`, `car_id`),
  CONSTRAINT `fk_users_cars_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_users_cars_cars` FOREIGN KEY (`car_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
);
