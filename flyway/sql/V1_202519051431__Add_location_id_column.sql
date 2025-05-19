ALTER TABLE `gameroom`.`user_consoles`
CHANGE COLUMN `` `location_id` INT NOT NULL ,
DROP INDEX `loc_id_idx` ;
;
