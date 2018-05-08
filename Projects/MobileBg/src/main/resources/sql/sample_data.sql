INSERT INTO `brands` VALUES
  (1, 'Brand A'),
  (2, 'Brand B'),
  (3, 'Brand C'),
  (4, 'Brand D');

INSERT INTO `models` VALUES
  (1, 'Model A', 1),
  (2, 'Model B', 2),
  (3, 'Model C', 3);

INSERT INTO `categories` VALUES
  (3, 'Bus'),
  (1, 'Car'),
  (2, 'Truck');

INSERT INTO `cities` VALUES
  (2, 'Plovdiv'),
  (1, 'Sofia'),
  (3, 'Varna');

INSERT INTO `colors` VALUES
  (3, 'Black'),
  (2, 'Blue'),
  (1, 'Red');

INSERT INTO `extras_categories` VALUES
  (1, 'Extra Cat 1'),
  (2, 'Extra Cat 2'),
  (3, 'Extra Cat 3');

INSERT INTO `extras` VALUES
  (1, '4x4', 1),
  (2, '7 seats', 1),
  (3, 'Registered', 1),
  (4, 'Alarm', 2),
  (5, '2(3) doors', 3),
  (6, 'LED lights', 3),
  (7, 'Armored', 2);

INSERT INTO `users` VALUES
  (1, 'pesho@abv.bg', 'peshotoo'),
  (2, 'gosho@abv.bg', 'goshi'),
  (3, 'stamat@abv.bg', 'dodo');

INSERT INTO `cars` VALUES
  (1, 1995, 120, 7350.00, 450000, 'USED', 'DIESEL', 'MANUAL', 1, 1, 1, 1, 1, 1),
  (2, 2000, 135, 11200.00, 380000, 'NEW', 'DIESEL', 'SEMI_AUTOMATIC', 2, 1, 2, 2, 2, 2),
  (3, 2012, 90, 9000.00, 500000, 'PARTS', 'HYBRID', 'AUTOMATIC', 3, 1, 3, 3, 3, 3);

INSERT INTO `cars_extras` VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (2, 1),
  (2, 7),
  (3, 3),
  (3, 1);
