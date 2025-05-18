ALTER TABLE `gameroom`.`locations`
ADD COLUMN `hours` VARCHAR(500) NOT NULL AFTER `phone`,
CHANGE COLUMN `ing` `lng` DOUBLE NOT NULL ;
