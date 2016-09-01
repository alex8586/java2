CREATE TABLE categories (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;

ALTER TABLE products
  ADD COLUMN category_id INT;
ALTER TABLE products
  ADD CONSTRAINT category_of_product FOREIGN KEY (category_id) REFERENCES categories (id)
  ON DELETE SET NULL;
