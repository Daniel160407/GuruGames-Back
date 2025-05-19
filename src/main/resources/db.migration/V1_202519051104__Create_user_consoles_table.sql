CREATE TABLE `gameroom`.`user_consoles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `console_id` INT NOT NULL,
  `booking_date` VARCHAR(45) NOT NULL,
  `booking_time` VARCHAR(45) NOT NULL,
  `duration` INT NOT NULL,
  `guests_num` INT NOT NULL,
  PRIMARY KEY (`id`));
