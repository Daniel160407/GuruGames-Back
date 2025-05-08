ALTER TABLE `gameroom`.`users`
CHANGE COLUMN `card_number` `card_number` VARCHAR(500) NOT NULL ,
CHANGE COLUMN `expiry` `expiry` VARCHAR(500) NOT NULL ,
CHANGE COLUMN `security_code` `security_code` VARCHAR(500) NOT NULL ;
