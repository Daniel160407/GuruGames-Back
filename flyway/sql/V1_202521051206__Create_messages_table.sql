CREATE TABLE `gameroom`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `message` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`));
