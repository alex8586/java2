package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.CrudDAOTest;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.order.Order;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class OrderORMDAOImplTest extends CrudDAOTest<Order, OrderORMDAOImpl> {

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;
    private Random random = new Random();
    private Category category;
    private Product product;
    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;

    @Before
    public void before() {
        cleaner.cleanDatabase();
        category = new Category();
        category.setName("name");
        categoryDAO.create(category);

        product = new Product();
        product.setName("name" + random.nextInt(100000));
        product.setDescription("description" + random.nextInt(100000));
        product.setPrice(random.nextInt(100000));
        product.setCategoryId(category.getId());
        product.setImgUrl("imgpath");
        productDAO.create(product);
        super.before();
    }

    @Override
    protected Order newRecord() {
        return new Order();
    }

    @Override
    protected void fillRecordWithData(Order order) {
        order.setAddress("address");
        order.setDocument("document");
        order.setPerson("person");
        order.setPhone("phone");
        order.setTotal(9001);
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
    }

    @Override
    protected void makeChangesInRecord(Order order) {
        order.setAddress("address2");
        order.setDocument("document2");
        order.setPerson("person2");
        order.setPhone("phone2");
        order.setTotal(9002);
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
    }

    @Override
    protected void compareRecords(Order order1, Order order2) {
        assertEquals(order1.getId(), order2.getId());
    }


    /*
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
        line.setOrder(order);

        Set<OrderLine> lines = new HashSet<OrderLine>();
        lines.add(line);
        order.setOrderLines(lines);

        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
        System.out.println(order);
        System.out.println(order.getId());
    }
    */
}
