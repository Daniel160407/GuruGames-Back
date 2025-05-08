ALTER TABLE `gameroom`.`users`
ADD COLUMN `name` VARCHAR(255) NOT NULL AFTER `id`,
ADD COLUMN `lastname` VARCHAR(255) NOT NULL AFTER `name`,
ADD COLUMN `email` VARCHAR(255) NULL AFTER `lastname`,
CHANGE COLUMN `phone_number` `phone_number` VARCHAR(255) NULL ;
