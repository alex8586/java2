SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2miska` DEFAULT CHARACTER SET utf8 ;
USE `java2miska` ;

-- -----------------------------------------------------
-- Table `java2miska`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `java2miska`.`user` ;
CREATE TABLE IF NOT EXISTS `java2miska`.`user` (
  user_id INT(11) PRIMARY KEY AUTO_INCREMENT,
  user_fullName VARCHAR(45) NOT NULL,
  user_email VARCHAR(45) NOT NULL,
  user_password VARCHAR(45) NOT NULL
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

-- -----------------------------------------------------
-- Table `java2miska`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2miska`.`categories` ;
CREATE TABLE IF NOT EXISTS `java2miska`.`categories` (
  `id`   INT(11)  NOT NULL AUTO_INCREMENT,
  `name` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `java2miska`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2miskatest`.`product`;
CREATE TABLE IF NOT EXISTS `java2miskatest`.`product`
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
  `catID_FK` INT(11),
  CONSTRAINT `category_FK` FOREIGN KEY (`catID_FK`) REFERENCES `java2miskatest`.`categories` (id))
    ENGINE = InnoDB
  AUTO_INCREMENT = 1
;
CREATE INDEX `category_FK` ON `java2miskatest`.`product` (`catID_FK`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;