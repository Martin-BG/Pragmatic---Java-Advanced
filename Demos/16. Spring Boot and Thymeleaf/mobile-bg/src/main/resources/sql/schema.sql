CREATE DATABASE mobilebg_demo;

USE mobilebg_demo;

CREATE TABLE user (
  id       INT         NOT NULL AUTO_INCREMENT,
  email    VARCHAR(25) NOT NULL,
  password VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE brand (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE model (
  id       INT         NOT NULL AUTO_INCREMENT,
  name     VARCHAR(20) NOT NULL,
  brand_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (brand_id) REFERENCES brand (id)
);

CREATE TABLE color (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE category (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE city (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE extra_category (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE extra (
  id                INT         NOT NULL AUTO_INCREMENT,
  name              VARCHAR(20) NOT NULL UNIQUE,
  extra_category_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (extra_category_id) REFERENCES extra_category (id)
);

CREATE TABLE car (
  id          INT                           NOT NULL AUTO_INCREMENT,
  brand_id    INT,
  model_id    INT,
  `condition` enum ('NEW', 'USED', 'PARTS') NOT NULL,
  year        INT                           NOT NULL,
  horse_power INT                           NOT NULL,
  price       DECIMAL                       NOT NULL,
  category_id INT,
  max_mileage INT                           NOT NULL,
  engine_type enum ('GASOLINE', 'DIESEL', 'ELECTRIC', 'HYBRID'),
  color_id    INT,
  gearbox     ENUM ('AUTOMATIC', 'MANUAL'),
  city_id     INT,
  PRIMARY KEY (id),
  FOREIGN KEY (brand_id) REFERENCES brand (id),
  FOREIGN KEY (model_id) REFERENCES model (id),
  FOREIGN KEY (color_id)
  REFERENCES color (id),
  FOREIGN KEY (category_id) REFERENCES category (id),
  FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE car_extra (
  id       INT NOT NULL AUTO_INCREMENT,
  car_id   INT,
  extra_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (car_id) REFERENCES car (id)
    ON DELETE CASCADE,
  FOREIGN KEY (extra_id) REFERENCES extra (id)
);

ALTER TABLE car
  ADD user_id INT,
  ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id);
