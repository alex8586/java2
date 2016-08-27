   sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('All products',0)");//1
            sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Fruits',1)");//2
            sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Vegetables',1)");//3
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Fresh',3)");//4
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Marinades',3)");//5

            sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Milk products',1)");//6
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Milk',6)");//7
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Cream',6)");//8
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Butter',6)");//9

            sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Meat',1)");//10
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Chicken',10)");//11
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Pork',10)");//12
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Beef',10)");//13
                sqlStatements.add("INSERT INTO categories(id,name,father_id) VALUES('Sausages',10)");//14

        /*fruits 1*/
        sqlStatements.add("INSERT INTO products(id,name,description,price,category_id) VALUES(default,'Small Green Aapples' ,'','img/products/fruits/01/',1.29,2)");//01

        sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Large Green Apples'  ,'','img/products/fruits/02/',1.11,2)");//2
        sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Large Red Apples'    ,'','img/products/fruits/03/',1.32,2)");//3
        sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Green Grapes'        ,'','img/products/fruits/04/',2.22,2)");//4
        sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Red Grapes'          ,'','img/products/fruits/05/',2.09,2)");//5
        sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Bananas Honduras'    ,'','img/products/fruits/06/',1.56,2)");//6
        sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Bananas Mexico'      ,'','img/products/fruits/07',1.65,2)");//7

        /*vegetable 2*/
            /*fresh 3*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Potatoes small','','img/products/vegetables/fresh/08/',0.79,4)");//8
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Potatoes large ','','img/products/vegetables/fresh/09/',1.01,4)");//9
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Onion natural','','img/products/vegetables/fresh/10/',0.65,4)");//10
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Carrots','','img/products/vegetables/fresh/11/',0.66,4)");//11
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Tomatoes large','','img/products/vegetables/fresh/12/',1.12,4)");//12
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Tomatoes medium','','img/products/vegetables/fresh/13/',1.43,4)");//13
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Tomatoes cherry','','img/products/vegetables/fresh/14/',1.89,4)");//14
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Cucumber tiny','','img/products/vegetables/fresh/15/',1.44,4)");//15
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Cucumber medium','','img/products/vegetables/fresh/16/',1.55,4)");//16

            /*marinadess 4*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Cucumber in marinade','','img/products/vegetables/marinades/17/',2.29,5)");//17
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Tomatoes in marinade','','img/products/vegetables/marinades/18/',3.02,5)");//18

        /*milk products 5*/
            /*milk 6*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Milk 2.5% first brand','','img/products/milkproducts/milk/19/',0.89,7)");//19
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Milk 2.5% second brand','','img/products/milkproducts/milk/20/',0.96,7)");//20
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Milk 3.5% first brand','','img/products/milkproducts/milk/21/',1.01,7)");//21
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Preserved milk third brand','','img/products/milkproducts/milk/22/',1.76,7)");//22

            /*cream 7*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Cream 15% second brand','','img/products/milkproducts/cream/23/',1.12,8)");//23
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Cream 15% first brand','','img/products/milkproducts/cream/24/',1.12,8)");//24
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Cream 25%second brand','','img/products/milkproducts/cream/25/',1.34,8)");//25

            /*butter 8*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Simple butter','','img/products/milkproducts/butter/26/',0.96,9)");//26
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Buttier than butter','','img/products/milkproducts/butter/27/',1.07,9)");//27

        /*meat 9*/
            /*chicken 10*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Chicken legs','','img/products/meat/chicken/28/',2.50,11)");//28
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Chicken wings','','img/products/meat/chicken/29/',2.60,11)");//29
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Whole chicken','','img/products/meat/chicken/30/',7.10,11)");//30

            /*pork 11*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Pork belly cut','','img/products/meat/pork/31/',3.33,12)");//31
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Pork ribs','','img/products/meat/pork/32/',4.44,12)");//32
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Bacon Yeahbaby','','img/products/meat/pork/33/',5.55,12)");//33
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Ham first brand','','img/products/meat/pork/34/',3.45,12)");//34
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Ham second brand','','img/products/meat/pork/35/',4.56,12)");//35
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Poignants Chunky Bacon','','img/products/meat/pork/36/',5.67,12)");//36

            /*beef 12*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Beef belly cut','','img/products/meat/beef/37/',4.44,13)");//37
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Beef ribs','','img/products/meat/beef/38/',4.53,13)");//38
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Beef legs','','img/products/meat/beef/39/',4.36,13)");//39
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Beef ribs another','','img/products/meat/beef/40/',5.12,13)");//40

            /*sausages 13*/
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Sausages','','img/products/meat/sausages/41/',2.33,14)");//41
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Sausages for grilling','','img/products/meat/sausages/42/',3.24,14)");//42
            sqlStatements.add("INSERT INTO products(id,name,description,imagepath,price,category_id) VALUES(default,'Salami','','img/products/meat/sausages/43/',4.54,14)");//43

        sqlStatements.add("UPDATE products SET products.description = CONCAT('desc for ',products.name) , products.imagepath = 'img/products/image_coming_soon.png'" );