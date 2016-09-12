package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.CrudDAOTest;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class OrderORMDAOImplTest extends CrudDAOTest<Order, OrderORMDAOImpl> {


    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;
    @Autowired
    @Qualifier("ORM_UserDAO")
    UserDAO userDAO;
    private Random random = new Random();
    private Category category;
    private Product product;
    private Product product2;
    private User user;
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

        product2 = new Product();
        product2.setName("name" + random.nextInt(100000));
        product2.setDescription("description" + random.nextInt(100000));
        product2.setPrice(random.nextInt(100000));
        product2.setCategoryId(category.getId());
        product2.setImgUrl("imgpath");
        productDAO.create(product2);

        user = new User();
        user.setEmail("mail@mail");
        user.setPassword("pass");
        user.setFullName("name");
        userDAO.create(user);


        super.before();
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
        order.setUserId(user.getId());

        OrderLine orderLine = new OrderLine();
        orderLine.setDescription("desc");
        orderLine.setExpireDate(new Date());
        orderLine.setQuantity(123);
        orderLine.setPrice(123);
        orderLine.setName("name");
        orderLine.setProductId(product.getId());
        orderLine.setOrder(order);
        List<OrderLine> lines = new ArrayList<>();
        lines.add(orderLine);
        order.setOrderLines(lines);
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

        OrderLine orderLine = new OrderLine();
        orderLine.setDescription("desc2");
        orderLine.setExpireDate(new Date());
        orderLine.setQuantity(124);
        orderLine.setPrice(124);
        orderLine.setName("name2");
        orderLine.setProductId(product2.getId());
        orderLine.setOrder(order);

        orderLine = new OrderLine();
        orderLine.setDescription("desc3");
        orderLine.setExpireDate(new Date());
        orderLine.setQuantity(125);
        orderLine.setPrice(125);
        orderLine.setName("name3");
        orderLine.setProductId(product2.getId());
        orderLine.setOrder(order);

        order.getOrderLines().add(orderLine);
    }

    @Override
    protected void compareRecords(Order order1, Order order2) {
        assertEquals(order1.getId(), order2.getId());
        assertEquals(order1.getUserId(), order2.getUserId());
        assertEquals(order1.getAddress(), order2.getAddress());
        assertEquals(DateUtils.truncate(order1.getDeliveryDate(), Calendar.DATE),
                DateUtils.truncate(order2.getDeliveryDate(), Calendar.DATE));
        assertEquals(DateUtils.truncate(order1.getOrderDate(), Calendar.DATE),
                DateUtils.truncate(order2.getOrderDate(), Calendar.DATE));
        assertEquals(order1.getDocument(), order2.getDocument());
        assertEquals(order1.getPerson(), order2.getPerson());
        assertEquals(order1.getPhone(), order2.getPhone());
        assertEquals(order1.getTotal(), order2.getTotal());

        assertEquals(order1.getOrderLines().size(), order2.getOrderLines().size());
        assertTrue(order1.getOrderLines().containsAll(order2.getOrderLines()));
    }

    @Test
    public void testGetOrderByUser() {
        List<Order> orderByUser = dao.getByUserId(user.getId());
        assertEquals(1, orderByUser.size());
        Order order = orderByUser.get(0);

        assertEquals(recordFromDAO.getId(), order.getId());
        assertEquals(recordFromDAO.getOrderLines().size(), order.getOrderLines().size());
    }

    @Test
    public void getMultipleOrdersByUsers() {
        Order secondOrder = newRecord();
        fillRecordWithData(secondOrder);
        makeChangesInRecord(secondOrder);
        dao.create(secondOrder);

        List<Order> ordersByUser = dao.getByUserId(user.getId());
        for (Order order : ordersByUser) {
            System.out.println(order + " " + order.getId());
            for (OrderLine orderLine : order.getOrderLines()) {
                System.out.println(orderLine.getId() + " " + orderLine.getOrder().getId());
            }

        }

        System.out.println(ordersByUser);

    }
}
