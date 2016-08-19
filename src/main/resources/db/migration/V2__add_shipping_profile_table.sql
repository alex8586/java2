CREATE TABLE shipping_profiles (
  id        INT(11) PRIMARY KEY AUTO_INCREMENT,
  person    VARCHAR(100) NOT NULL,
  document  VARCHAR(50)  NOT NULL,
  address   VARCHAR(100) NOT NULL,
  phone     VARCHAR(15)  NOT NULL,
  userID_FK INT(11),
  INDEX userID_FK(userID_FK),
  CONSTRAINT FOREIGN KEY (`userID_FK`) REFERENCES `java2miska`.`users` (`id`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;