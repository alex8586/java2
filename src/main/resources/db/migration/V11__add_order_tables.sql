CREATE TABLE orders (
  id            BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  person        VARCHAR(255)           NOT NULL,
  document      VARCHAR(255)           NOT NULL,
  address       VARCHAR(255)           NOT NULL,
  phone         VARCHAR(50)            NOT NULL,
  order_date    DATE                   NOT NULL,
  delivery_date DATE                   NOT NULL,
  total         BIGINT                 NOT NULL,
  user_id       BIGINT(11)
);

CREATE TABLE order_lines (
  id          BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  order_id    BIGINT(11),
  product_id  BIGINT(11),
  name        VARCHAR(255)           NOT NULL,
  description VARCHAR(1024),
  price       BIGINT                 NOT NULL DEFAULT 0,
  quantity    BIGINT                 NOT NULL DEFAULT 0,
  expire_date DATE                   NOT NULL,

  CONSTRAINT FOREIGN KEY (order_id) REFERENCES orders (id)
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id)
    ON DELETE SET NULL
);




