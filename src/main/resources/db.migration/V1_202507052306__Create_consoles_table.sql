CREATE TABLE `gameroom`.`consoles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `state` INT NOT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `userId_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `userId`
    FOREIGN KEY (`user_id`)
    REFERENCES `gameroom`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
