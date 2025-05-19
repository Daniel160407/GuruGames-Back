ALTER TABLE `gameroom`.`user_consoles`
ADD CONSTRAINT `us_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `gameroom`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `con_id`
  FOREIGN KEY (`console_id`)
  REFERENCES `gameroom`.`consoles` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
