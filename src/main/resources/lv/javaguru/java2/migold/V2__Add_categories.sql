CREATE TABLE categories (
  id   INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name TINYTEXT NOT NULL
);

ALTER TABLE products
  ADD COLUMN category_id INT;
ALTER TABLE products
  ADD FOREIGN KEY (category_id) REFERENCES categories (id);
