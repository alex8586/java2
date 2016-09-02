CREATE TABLE stock (
  id          BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  quantity    INT(11)                NOT NULL,
  expire_date DATE                   NOT NULL,
  product_id  BIGINT(11)             NOT NULL,
  CONSTRAINT stock_product_id FOREIGN KEY (product_id) REFERENCES products (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;