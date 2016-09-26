CREATE OR REPLACE VIEW productRate AS
  SELECT
    rate.product_id,
    avg(rate.rate) AS avg_rate,
    count(rate.id) AS rate_count
  FROM rate
  GROUP BY rate.product_id;

CREATE OR REPLACE VIEW productUserViews AS
  SELECT
    users_counter.product_id,
    sum(users_counter.counter) AS user_visits
  FROM users_counter
  GROUP BY users_counter.product_id;

CREATE OR REPLACE VIEW productVisitorViews AS
  SELECT
    visitors_counter.product_id,
    sum(visitors_counter.counter) AS visitor_visits
  FROM visitors_counter
  GROUP BY visitors_counter.product_id;

CREATE OR REPLACE VIEW productReviewCount AS
  SELECT
    reviews.product_id,
    count(reviews.id) AS review_count
  FROM reviews
  GROUP BY reviews.product_id;

CREATE OR REPLACE VIEW product_statistics AS
  SELECT
    products.id                                     AS product_id,
    products.name                                   AS product_name,
    categories.id                                   as category_id, categories.name as category_name,
    COALESCE(productReviewCount.review_count, 0)    AS review_count,
    COALESCE(productUserViews.user_visits, 0)       AS user_visits,
    COALESCE(productVisitorViews.visitor_visits, 0) AS visitor_visits,
    COALESCE(productRate.avg_rate, 0)               AS avg_rate
  from products
    LEFT outer join categories
      ON products.category_id = categories.id
    LEFT OUTER JOIN productReviewCount
      ON productReviewCount.product_id = products.id
    LEFT OUTER JOIN productVisitorViews
      ON productVisitorViews.product_id = products.id
    LEFT OUTER JOIN productUserViews
      ON productUserViews.product_id = products.id
    LEFT OUTER JOIN productRate
      ON productRate.product_id = products.id
  group by products.id;