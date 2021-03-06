CREATE TABLE rate (
  id         BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id    BIGINT(11)             NOT NULL,
  product_id BIGINT(11)             NOT NULL,
  rate       INT,
  CONSTRAINT rate_product_id FOREIGN KEY (product_id) REFERENCES products (id),
  CONSTRAINT rate_user_id FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;