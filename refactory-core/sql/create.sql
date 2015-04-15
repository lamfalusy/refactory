
CREATE TABLE IF NOT EXISTS `refactory`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login_name` VARCHAR(45) NOT NULL,
  `full_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_name_UNIQUE` (`login_name` ASC));


CREATE TABLE IF NOT EXISTS `refactory`.`projects` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created` DATE NOT NULL,
  `description` VARCHAR(1023) NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE IF NOT EXISTS `refactory`.`tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `project_fk` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `priority` VARCHAR(45) NULL,
  `reporter_fk` INT NOT NULL,
  `assignee_fk` INT NULL,
  `created` DATE NOT NULL,
  `deadline` DATE NULL,
  `description` VARCHAR(1023) NULL,
  PRIMARY KEY (`id`),
  INDEX `ticket_user_a_idx` (`assignee_fk` ASC),
  INDEX `ticket_user_r_idx` (`reporter_fk` ASC),
  INDEX `ticket_project_idx` (`project_fk` ASC),
  CONSTRAINT `ticket_user_a`
    FOREIGN KEY (`assignee_fk`)
    REFERENCES `refactory`.`users` (`id`),
  CONSTRAINT `ticket_user_r`
    FOREIGN KEY (`reporter_fk`)
    REFERENCES `refactory`.`users` (`id`),
  CONSTRAINT `ticket_project`
    FOREIGN KEY (`project_fk`)
    REFERENCES `refactory`.`projects` (`id`));
  
  
CREATE TABLE IF NOT EXISTS `refactory`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(1023) NOT NULL,
  `added` VARCHAR(45) NOT NULL,
  `user_fk` INT NOT NULL,
  `ticket_fk` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `comment_user_idx` (`user_fk` ASC),
  INDEX `comment_ticket_idx` (`ticket_fk` ASC),
  CONSTRAINT `comment_user`
    FOREIGN KEY (`user_fk`)
    REFERENCES `refactory`.`users` (`id`),
  CONSTRAINT `comment_ticket`
    FOREIGN KEY (`ticket_fk`)
    REFERENCES `refactory`.`tickets` (`id`));


CREATE TABLE IF NOT EXISTS `refactory`.`projects_users_SW` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_fk` INT NOT NULL,
  `project_fk` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `pusw_project_idx` (`project_fk` ASC),
  INDEX `pusw_user_idx` (`user_fk` ASC),
  CONSTRAINT `pusw_project`
    FOREIGN KEY (`project_fk`)
    REFERENCES `refactory`.`projects` (`id`),
  CONSTRAINT `pusw_user`
    FOREIGN KEY (`user_fk`)
    REFERENCES `refactory`.`users` (`id`));
