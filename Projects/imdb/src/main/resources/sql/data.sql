INSERT INTO `genres` (`name`) VALUES
  ('Action'),
  ('Comedy'),
  ('Drama'),
  ('Fantasy'),
  ('Adventure'),
  ('Sci-Fi'),
  ('Romance'),
  ('Horror'),
  ('Mistery'),
  ('Thriller'),
  ('Crime'),
  ('Animation'),
  ('Hisory'),
  ('Documentary'),
  ('Musical'),
  ('Biography'),
  ('War'),
  ('Sport'),
  ('Western');

INSERT INTO `movies` (`title`, `year`) VALUES
  ('Dunkirk', 2017),
  ('12 Strong', 2018),
  ('The Post', 2017);

INSERT INTO `movies_genres` (`movie_id`, `genre_id`) VALUES
  (1, 1), (1, 3), (1, 13),
  (2, 1), (2, 3), (2, 13),
  (3, 16), (3, 3), (3, 13);

INSERT INTO `actors` (`name`) VALUES
  ('Chris Hemsworth'),
  ('Michael Shannon'),
  ('Michael Peña'),
  ('Fionn Whitehead'),
  ('Barry Keoghan'),
  ('Mark Rylance'),
  ('Meryl Streep'),
  ('Tom Hanks'),
  ('Sarah Paulson');

INSERT INTO `movies_actors` (`movie_id`, `actor_id`) VALUES
  (1, 4), (1, 5), (1, 6),
  (2, 1), (2, 2), (2, 3),
  (3, 7), (3, 8), (3, 9);

INSERT INTO `users` (`email`, `password`) VALUES
  ('pesho@abv.bg', 'pesho'),
  ('gosho@abv.bg', 'gosho');

INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) VALUES
  (1, 1, 5), (2, 1, 7), (3, 1, 4),
  (1, 2, 10), (2, 2, 9), (3, 2, 6);

INSERT INTO `users_movies` (`user_id`, `movie_id`) VALUES
  (1, 1), (2, 2), (2, 3);
