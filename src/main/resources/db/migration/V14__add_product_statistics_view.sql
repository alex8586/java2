create view product_statistics as
  select  products.id as product_id , products.name as product_name,
          categories.id as category_id, categories.name as category_name,
          count(reviews.id) as review_count,
          COALESCE(sum(users_counter.counter),0) as user_visits ,
          COALESCE(sum(visitors_counter.counter),0) as visitor_visits,
          COALESCE(avg(rate.rate),0) as avg_rate
  from products
    LEFT outer join categories
      ON products.category_id = categories.id
    LEFT outer join reviews
      ON reviews.product_id = products.id
    LEFT outer join visitors_counter
      ON visitors_counter.product_id = products.id
    LEFT outer join users_counter
      ON users_counter.product_id = products.id
    LEFT outer join rate
      ON rate.product_id = products.id
  group by products.id;