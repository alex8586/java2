CREATE VIEW product_statistics AS
  SELECT
    products.id                                AS product_id,
    products.name                              AS product_name,
    categories.id                              AS category_id,
    categories.name                            AS category_name,
    count(reviews.id)                          AS review_count,
    COALESCE(sum(users_counter.counter), 0)    AS user_visits,
    COALESCE(sum(visitors_counter.counter), 0) AS visitor_visits,
    COALESCE(avg(rate.rate), 0)                AS avg_rate
  FROM products
    LEFT OUTER JOIN categories
      ON products.category_id = categories.id
    LEFT OUTER JOIN reviews
      ON reviews.product_id = products.id
    LEFT OUTER JOIN visitors_counter
      ON visitors_counter.product_id = products.id
    LEFT OUTER JOIN users_counter
      ON users_counter.product_id = products.id
    LEFT OUTER JOIN rate
      ON rate.product_id = products.id
  GROUP BY products.id;