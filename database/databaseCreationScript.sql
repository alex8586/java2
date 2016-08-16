SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2miska` DEFAULT CHARACTER SET utf8 ;
USE `java2miska` ;

-- -----------------------------------------------------
-- Table `java2miska`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `java2miska`.`users` ;
CREATE TABLE IF NOT EXISTS `java2miska`.`users` (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX users_email_uindex ON java2miska.users (email);

-- -----------------------------------------------------
-- Table `java2miska`.`categories`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `java2miska`.`categories` ;
CREATE TABLE IF NOT EXISTS `java2miska`.`categories` (
  `id`   INT(11)  NOT NULL AUTO_INCREMENT,
  `name` CHAR(64) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `java2miska`.`product`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `java2miska`.`product`;
CREATE TABLE IF NOT EXISTS `java2miska`.`product`
(
  `ProductID` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `VendorCode` VARCHAR(10),
  `VendorName` VARCHAR(60),
  `VendorDescription` VARCHAR(300),
  `unit` VARCHAR(30),
  `price` INT(11),
  `DisplayName` VARCHAR(60),
  `DisplayDescription` VARCHAR(400),
  `RemainQTY` INT(11),
  `catID_FK` INT(11)
#   ,CONSTRAINT `category_FK` FOREIGN KEY (`catID_FK`) REFERENCES `java2miska`.`categories` (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

# CREATE INDEX `category_FKI` ON `java2miska`.`product` (`catID_FK`);
# CREATE UNIQUE INDEX `VendorCode_UNIQUE` ON `java2miska`.`product` (`VendorCode`);

INSERT INTO categories(id,name) VALUES(DEFAULT ,"Соки и нектары 'моя печенюшечка'");
INSERT INTO categories(id,name) VALUES(DEFAULT ,"Закусь");
INSERT INTO categories(id,name) VALUES(DEFAULT ,"Товары для поклонниц Стасика Михайлова");



INSERT INTO product(ProductID, DisplayName, DisplayDescription, RemainQTY) VALUES (DEFAULT,"Морква","СуперМорква в ящиках по 250 килограмм",4);
INSERT INTO product(ProductID, DisplayName, DisplayDescription, RemainQTY) VALUES (DEFAULT,"Морква","СуперМорква в ящиках по 250 килограмм",4);
INSERT INTO product(ProductID, DisplayName, DisplayDescription, RemainQTY) VALUES (DEFAULT,"Морква","СуперМорква в ящиках по 250 килограмм",4);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;