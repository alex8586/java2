ALTER TABLE stock
  MODIFY COLUMN product_id BIGINT(11);

ALTER TABLE reviews
  MODIFY COLUMN product_id BIGINT(11);

ALTER TABLE rate
  MODIFY COLUMN product_id BIGINT(11);