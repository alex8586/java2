CREATE TABLE visitors_counter (
  id         BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ip         VARCHAR(30)            NOT NULL,
  product_id BIGINT(11),
  counter    INT                    NOT NULL,
  CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id)
    ON DELETE SET NULL
);

CREATE TABLE users_counter (
  id         BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id    BIGINT(11),
  product_id BIGINT(11),
  counter    INT                    NOT NULL,
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id)
    ON DELETE SET NULL
);