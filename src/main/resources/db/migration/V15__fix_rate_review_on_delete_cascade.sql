ALTER TABLE rate DROP FOREIGN KEY rate_product_id;
ALTER TABLE rate
  ADD CONSTRAINT rate_product_id
FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE reviews DROP FOREIGN KEY review_product_id;
ALTER TABLE reviews
  ADD CONSTRAINT review_product_id
FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE order_lines DROP FOREIGN KEY order_lines_ibfk_2;
ALTER TABLE order_lines
  ADD CONSTRAINT order_lines_ibfk_2
FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE stock DROP FOREIGN KEY stock_product_id;
ALTER TABLE stock
  ADD CONSTRAINT stock_product_id
FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;