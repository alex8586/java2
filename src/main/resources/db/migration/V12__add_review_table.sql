CREATE TABLE reviews (
  id         BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id    BIGINT(11)             NOT NULL,
  product_id BIGINT(11)             NOT NULL,
  review     VARCHAR(1024)          NOT NULL,
  user_name  VARCHAR(45)            NOT NULL,
  date       DATE                   NOT NULL,
  CONSTRAINT review_product_id FOREIGN KEY (product_id) REFERENCES products (id),
  CONSTRAINT review_user_id FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;