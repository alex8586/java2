DROP TABLE IF EXISTS products;
CREATE TABLE products
(
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