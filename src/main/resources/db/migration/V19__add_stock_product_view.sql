CREATE OR REPLACE VIEW stock_product AS
  SELECT stock.id as stock_id,
         stock.product_id as product_id,
         stock.quantity as quantity,
         stock.expire_date as expire_date,
         products.name as product_name
  from stock
    LEFT outer join products
      on products.id = stock.product_id;