ALTER TABLE products
  DROP FOREIGN KEY category_of_product;
ALTER TABLE shipping_profiles
  DROP FOREIGN KEY user_of_shipping_profile;

ALTER TABLE products
  MODIFY COLUMN id BIGINT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE shipping_profiles
  MODIFY COLUMN id BIGINT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE categories
  MODIFY COLUMN id BIGINT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE products
  MODIFY COLUMN category_id BIGINT(11);

ALTER TABLE users
  MODIFY COLUMN id BIGINT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE shipping_profiles
  MODIFY COLUMN user_id BIGINT(11);

ALTER TABLE products
  ADD CONSTRAINT category_of_product FOREIGN KEY (category_id) REFERENCES categories (id)
  ON DELETE SET NULL;

ALTER TABLE shipping_profiles
  ADD CONSTRAINT user_of_shipping_profile FOREIGN KEY (user_id) REFERENCES users (id)
  ON DELETE CASCADE;

ALTER TABLE categories
  MODIFY COLUMN father_id BIGINT(11);


