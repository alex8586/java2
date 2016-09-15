USE java2miska;

DELETE FROM products;
DELETE FROM categories;
DELETE FROM users;
DELETE FROM order_lines;
DELETE FROM orders;
DELETE FROM shipping_profiles;
DELETE FROM users_counter;
DELETE FROM visitors_counter;

ALTER TABLE categories
  AUTO_INCREMENT = 1;
ALTER TABLE products
  AUTO_INCREMENT = 1;

INSERT INTO categories (id, name, father_id) VALUES (default, 'All products', 0);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Fruits', 1);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Vegetables', 1);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Fresh', 3);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Marinades', 3);

INSERT INTO categories (id, name, father_id) VALUES (default, 'Milk products', 1);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Milk', 6);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Cream', 6);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Butter', 6);

INSERT INTO categories (id, name, father_id) VALUES (default, 'Meat', 1);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Chicken', 10);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Pork', 10);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Beef', 10);
INSERT INTO categories (id, name, father_id) VALUES (default, 'Sausages', 10);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Small Green apples', '', 'img/products/fruits/01/', 1.29, 2);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Large Green Apples', '', 'img/products/fruits/02/', 1.11, 2);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Large Red Apples', '', 'img/products/fruits/03/', 1.32, 2);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Green Grapes', '', 'img/products/fruits/04/', 2.22, 2);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Red Grapes', '', 'img/products/fruits/05/', 2.09, 2);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Bananas Honduras', '', 'img/products/fruits/06/', 1.56, 2);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Bananas Mexico', '', 'img/products/fruits/07', 1.65, 2);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Potatoes small', '', 'img/products/vegetables/fresh/08/', 0.79, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Potatoes large ', '', 'img/products/vegetables/fresh/09/', 1.01, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Onion natural', '', 'img/products/vegetables/fresh/10/', 0.65, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Carrots', '', 'img/products/vegetables/fresh/11/', 0.66, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Tomatoes large', '', 'img/products/vegetables/fresh/12/', 1.12, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Tomatoes medium', '', 'img/products/vegetables/fresh/13/', 1.43, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Tomatoes cherry', '', 'img/products/vegetables/fresh/14/', 1.89, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Cucumber tiny', '', 'img/products/vegetables/fresh/15/', 1.44, 4);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Cucumber medium', '', 'img/products/vegetables/fresh/16/', 1.55, 4);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Cucumber in marinade', '', 'img/products/vegetables/marinades/17/', 2.29, 5);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Tomatoes in marinade', '', 'img/products/vegetables/marinades/18/', 3.02, 5);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Milk 2.5% first brand', '', 'img/products/milkproducts/milk/19/', 0.89, 7);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Milk 2.5% second brand', '', 'img/products/milkproducts/milk/20/', 0.96, 7);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Milk 3.5% first brand', '', 'img/products/milkproducts/milk/21/', 1.01, 7);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Preserved milk third brand', '', 'img/products/milkproducts/milk/22/', 1.76, 7);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Cream 15% second brand', '', 'img/products/milkproducts/cream/23/', 1.12, 8);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Cream 15% first brand', '', 'img/products/milkproducts/cream/24/', 1.12, 8);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Cream 25%second brand', '', 'img/products/milkproducts/cream/25/', 1.34, 8);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Simple butter', '', 'img/products/milkproducts/butter/26/', 0.96, 9);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Buttier than butter', '', 'img/products/milkproducts/butter/27/', 1.07, 9);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Chicken legs', '', 'img/products/meat/chicken/28/', 2.50, 11);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Chicken wings', '', 'img/products/meat/chicken/29/', 2.60, 11);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Whole chicken', '', 'img/products/meat/chicken/30/', 7.10, 11);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Pork belly cut', '', 'img/products/meat/pork/31/', 3.33, 12);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Pork ribs', '', 'img/products/meat/pork/32/', 4.44, 12);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Bacon Yeahbaby', '', 'img/products/meat/pork/33/', 5.55, 12);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Ham first brand', '', 'img/products/meat/pork/34/', 3.45, 12);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Ham second brand', '', 'img/products/meat/pork/35/', 4.56, 12);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Poignants Chunky Bacon', '', 'img/products/meat/pork/36/', 5.67, 12);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Beef belly cut', '', 'img/products/meat/beef/37/', 4.44, 13);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Beef ribs', '', 'img/products/meat/beef/38/', 4.53, 13);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Beef legs', '', 'img/products/meat/beef/39/', 4.36, 13);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Beef ribs another', '', 'img/products/meat/beef/40/', 5.12, 13);

INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Sausages', '', 'img/products/meat/sausages/41/', 2.33, 14);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Sausages for grilling', '', 'img/products/meat/sausages/42/', 3.24, 14);
INSERT INTO products (id, name, description, imgurl, price, category_id)
VALUES (default, 'Salami', '', 'img/products/meat/sausages/43/', 4.54, 14);

UPDATE products
SET products.description = CONCAT('desc for ', products.name);
UPDATE products
SET products.imgurl = "img/products/image_coming_soon.png";


INSERT INTO stock (product_id, quantity, expire_date)
  SELECT
    products.id,
    1,
    '2017-01-20'
  FROM products;

INSERT INTO stock (product_id, quantity, expire_date)
  SELECT
    products.id,
    3,
    '2017-01-25'
  FROM products;

INSERT INTO stock (product_id, quantity, expire_date)
  SELECT
    products.id,
    2,
    '2017-01-22'
  FROM products;





