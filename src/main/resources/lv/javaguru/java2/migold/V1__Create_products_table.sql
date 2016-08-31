CREATE TABLE products (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description TEXT,
  price       INT          NOT NULL DEFAULT 0,
  qty
);
