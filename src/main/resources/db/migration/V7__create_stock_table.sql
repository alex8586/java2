CREATE TABLE stock (
  id            INT(11) PRIMARY KEY AUTO_INCREMENT,
  quantity      INT(11) NOT NULL,
  expire_date   DATE    NOT NULL,
  product_ID_FK INT(11) NOT NULL,
  INDEX productID_FK(product_ID_FK),
  CONSTRAINT FOREIGN KEY (`product_ID_FK`) REFERENCES users (`id`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;