CREATE TABLE users (
  id       INT(11) PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(45) NOT NULL,
  email    VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  UNIQUE INDEX email (email)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


CREATE TABLE categories (
  id   INT(11)  NOT NULL AUTO_INCREMENT,
  name CHAR(64) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS products(
  ProductID          INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  VendorCode         VARCHAR(10),
  VendorName         VARCHAR(60),
  VendorDescription  VARCHAR(300),
  unit               VARCHAR(30),
  price              INT(11),
  DisplayName        VARCHAR(60),
  DisplayDescription VARCHAR(400),
  RemainQTY          INT(11),
  catID_FK           INT(11),
  CONSTRAINT category_FK FOREIGN KEY (catID_FK) REFERENCES categories (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;