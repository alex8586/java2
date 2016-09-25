ALTER TABLE users_counter DROP FOREIGN KEY users_counter_ibfk_2;
ALTER TABLE users_counter
  ADD CONSTRAINT users_counter_ibfk_2
FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE visitors_counter DROP FOREIGN KEY visitors_counter_ibfk_1;
ALTER TABLE visitors_counter
  ADD CONSTRAINT visitors_counter_ibfk_1
FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;