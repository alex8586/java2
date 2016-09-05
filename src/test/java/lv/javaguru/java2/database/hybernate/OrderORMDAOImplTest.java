package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class OrderORMDAOImplTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;

    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    @Rollback(false)
    @Test
    public void test() {

        Category category = new Category();
        category.setName("name");
        categoryDAO.create(category);

        Random random = new Random();
        Product product = new Product();
        product.setName("name" + random.nextInt(100000));
        product.setDescription("description" + random.nextInt(100000));
        product.setPrice(random.nextInt(100000));
        product.setCategoryId(category.getId());
        product.setImgUrl("imgpath");
        productDAO.create(product);


        Order order = new Order();
        order.setAddress("addr");
        order.setDocument("doc");
        order.setPerson("pers");
        order.setPhone("phone");
        order.setTotal(9001);
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());

        OrderLine line = new OrderLine();
        line.setName("line");
        line.setPrice(123);
        line.setExpireDate(new Date());
        line.setProductId(product.getId());
        line.setDescription("dewc");

        List<OrderLine> lines = new ArrayList<OrderLine>();
        lines.add(line);
        order.setOrderLines(lines);

        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
        System.out.println(order);
        System.out.println(order.getId());
    }
}
