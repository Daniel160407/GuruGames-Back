CREATE TABLE `gameroom`.`locations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(500) NOT NULL,
  `address` VARCHAR(500) NOT NULL,
  `phone` VARCHAR(500) NOT NULL,
  `features` VARCHAR(500) NOT NULL,
  `lat` DOUBLE NOT NULL,
  `ing` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));
