INSERT INTO `genres` (`name`) VALUES
  ('Action'),
  ('Comedy'),
  ('Drama'),
  ('Fantasy'),
  ('Adventure'),
  ('Sci-Fi'),
  ('Romance'),
  ('Horror'),
  ('Mystery'),
  ('Thriller'),
  ('Crime'),
  ('Animation'),
  ('History'),
  ('Documentary'),
  ('Musical'),
  ('Biography'),
  ('War'),
  ('Sport'),
  ('Western');

INSERT INTO `movies` (`title`, `year`) VALUES
  ('Dunkirk', 2017),
  ('12 Strong', 2018),
  ('The Post', 2017),
  ('Saving Private Ryan', 1998);

INSERT INTO `movies_genres` (`movie_id`, `genre_id`) VALUES
  (1, 1), (1, 3), (1, 13),
  (2, 1), (2, 3), (2, 13),
  (3, 16), (3, 3), (3, 13),
  (4, 3), (4, 13), (4, 17);

INSERT INTO `actors` (`name`) VALUES
  ('Chris Hemsworth'),
  ('Michael Shannon'),
  ('Michael Peña'),
  ('Fionn Whitehead'),
  ('Barry Keoghan'),
  ('Mark Rylance'),
  ('Meryl Streep'),
  ('Tom Hanks'),
  ('Sarah Paulson'),
  ('Matt Damon'),
  ('Tom Sizemore');

INSERT INTO `movies_actors` (`movie_id`, `actor_id`) VALUES
  (1, 4), (1, 5), (1, 6),
  (2, 1), (2, 2), (2, 3),
  (3, 7), (3, 8), (3, 9),
  (4, 8), (4, 10), (4, 11);

INSERT INTO `users` (`email`, `password`) VALUES
  ('pesho@abv.bg', 'pesho'),
  ('gosho@abv.bg', 'gosho');

INSERT INTO `movies_user_ratings` (`movie_id`, `user_id`, `rating`) VALUES
  (1, 1, 5), (2, 1, 7), (3, 1, 4), (4, 1, 9),
  (1, 2, 10), (2, 2, 9), (3, 2, 6), (4, 2, 8);

INSERT INTO `users_movies` (`user_id`, `movie_id`) VALUES
  (1, 1), (2, 2), (2, 3), (1, 4);

INSERT INTO `posters` (`movie_id`, `url`) VALUES
  (4, 'http://i0.kym-cdn.com/photos/images/original/001/254/427/32c.jpg'),
  (4,
   'https://m.media-amazon.com/images/M/MV5BZTAwMzAxNDEtM2FiNC00ODY2LTk1ZTctOTkyNmJlMmE0Y2IzXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SY1000_CR0,0,682,1000_AL_.jpg'),
  (3, 'http://www.impawards.com/2017/posters/post_ver2_xlg.jpg');

INSERT INTO `trailers` (`movie_id`, `url`) VALUES
  (4, 'https://www.youtube.com/embed/RYID71hYHzg'),
  (4, 'https://www.youtube.com/embed/yiyjRA7X2hg'),
  (3, 'https://www.youtube.com/embed/nrXlY6gzTTM');
