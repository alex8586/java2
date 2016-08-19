CREATE TABLE products (
  id          INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        TINYTEXT NOT NULL,
  description TEXT,
  price       INT      NOT NULL DEFAULT 0
);
