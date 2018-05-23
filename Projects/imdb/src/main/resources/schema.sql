DROP DATABASE IF EXISTS `imdb`;

CREATE DATABASE `imdb`;

USE `imdb`;

CREATE TABLE `movies` (
  `id`    INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50)     NOT NULL UNIQUE,
  `year`  INT(4) UNSIGNED NOT NULL
);

CREATE TABLE `trailers` (
  `id`       INT PRIMARY KEY AUTO_INCREMENT,
  `movie_id` INT          NOT NULL,
  `url`      VARCHAR(255) NOT NULL UNIQUE,
  CONSTRAINT `fk_trailers_movies` FOREIGN KEY (`movie_id`)
  REFERENCES `movies` (`id`)
    ON DELETE CASCADE
);

CREATE TABLE `posters` (
  `id`       INT PRIMARY KEY AUTO_INCREMENT,
  `movie_id` INT          NOT NULL,
  `url`      VARCHAR(255) NOT NULL UNIQUE,
  CONSTRAINT `fk_posters_movies` FOREIGN KEY (`movie_id`)
  REFERENCES `movies` (`id`)
    ON DELETE CASCADE
);

CREATE TABLE `actors` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE `genres` (
  `id`   INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) UNIQUE
);

CREATE TABLE `movies_genres` (
  `movie_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  CONSTRAINT `fk_movies_genres_movies` FOREIGN KEY (`movie_id`)
  REFERENCES `movies` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_movies_genres_genres` FOREIGN KEY (`genre_id`)
  REFERENCES `genres` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `pk_movie_genre` PRIMARY KEY (`movie_id`, `genre_id`)
);

CREATE TABLE `movies_actors` (
  `movie_id` INT NOT NULL,
  `actor_id` INT NOT NULL,
  CONSTRAINT `fk_movies_actors_movies` FOREIGN KEY (`movie_id`)
  REFERENCES `movies` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_movies_actors_actors` FOREIGN KEY (`actor_id`)
  REFERENCES `actors` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `pk_movie_actor` PRIMARY KEY (`movie_id`, `actor_id`)
);

CREATE TABLE `users` (
  `id`       INT PRIMARY KEY AUTO_INCREMENT,
  `email`    VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(50) NOT NULL
);

CREATE TABLE `movies_user_ratings` (
  `movie_id` INT     NOT NULL,
  `user_id`  INT     NOT NULL,
  `rating`   TINYINT NOT NULL,
  CONSTRAINT `fk_movies_user_ratings_movies` FOREIGN KEY (`movie_id`)
  REFERENCES `movies` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_movies_user_ratings_users` FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `pk_movies_user_ratings` PRIMARY KEY (`movie_id`, `user_id`)
);

CREATE TABLE `users_movies` (
  `user_id`  INT NOT NULL,
  `movie_id` INT NOT NULL,
  CONSTRAINT `fk_users_movies_users` FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_users_movies_movies` FOREIGN KEY (`movie_id`)
  REFERENCES `movies` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `pk_users_movies` PRIMARY KEY (`user_id`, `movie_id`)
);
