CREATE TABLE products (
  id          BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(500),
  price       BIGINT NOT NULL DEFAULT 0,
  imgurl      VARCHAR(100) NOT NULL
);

CREATE TABLE categories (
  id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  father_id BIGINT NOT NULL
);

ALTER TABLE products
  ADD COLUMN category_id BIGINT(11);
ALTER TABLE products
  ADD FOREIGN KEY (category_id) REFERENCES categories (id)
  ON DELETE SET NULL;

CREATE TABLE users (
  id       BIGINT(11)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  UNIQUE (email)
);

CREATE TABLE shipping_profiles (
  id       BIGINT(11)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  person   VARCHAR(255) NOT NULL,
  document VARCHAR(255) NOT NULL,
  address  VARCHAR(255) NOT NULL,
  phone    VARCHAR(50)  NOT NULL,
  user_id  BIGINT(11),
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE visitors_counter(
  id BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ip VARCHAR(30) NOT NULL,
  product_id BIGINT(11),
  counter BIGINT(11) NOT NULL,
  CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id)
    ON DELETE SET NULL
);

CREATE TABLE users_counter(
  id BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT(11),
  product_id BIGINT(11),
  counter BIGINT(11) NOT NULL,
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (product_id) REFERENCES products (id)
    ON DELETE SET NULL
);

-- clean and truncate -----------
SET FOREIGN_KEY_CHECKS=0;
DELETE FROM categories;
DELETE FROM products;
DELETE FROM shipping_profiles;
DELETE FROM users;
DELETE FROM users_counter;
DELETE FROM visitors_counter;
DELETE FROM rate;
TRUNCATE TABLE rate;
TRUNCATE TABLE categories;
TRUNCATE TABLE products;
TRUNCATE TABLE shipping_profiles;
TRUNCATE TABLE users;
TRUNCATE TABLE users_counter;
TRUNCATE TABLE visitors_counter;
SET FOREIGN_KEY_CHECKS=1;
