ALTER TABLE `gameroom`.`users`
ADD COLUMN `card_number` VARCHAR(100) NOT NULL AFTER `password`,
ADD COLUMN `expiry` VARCHAR(45) NOT NULL AFTER `card_number`,
ADD COLUMN `security_code` INT NOT NULL AFTER `expiry`,
CHANGE COLUMN `email` `phone_number` VARCHAR(255) NOT NULL ;
