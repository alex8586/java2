package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.*;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


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
    @Qualifier("ORM_UserDAO")
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDAO orderDAO;

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
    protected void fillRecordWithData(Order order) {
        order.setAddress("address");
        order.setDocument("document");
        order.setPerson("person");
        order.setPhone("phone");
        order.setTotal(9001);
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());

        OrderLine orderLine = new OrderLine();
        orderLine.setDescription("desc");
        orderLine.setExpireDate(new Date());
        orderLine.setQuantity(123);
        orderLine.setPrice(123);
        orderLine.setName("name");
        orderLine.setProductId(product.getId());
        orderLine.setOrderId(order.getId());
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
    }

    @Test
    public void getByUserIdTest(){
        User first = getUser();
        long firstId = first.getId();
        User second = getAnotherUser();

        createManyOrders(first);
        createManyOrders(second);

        List<Order> listOrders = orderDAO.getByUserId(firstId);

        for(Order order : listOrders){
            assertEquals(first.getFullName(), order.getPerson());
            assertNotEquals(second.getFullName(), order.getPerson());
        }

    }

    private User getUser(){
        User user = new User();
        user.setFullName("name");
        user.setEmail("email@mail.com");
        user.setPassword("password");
        userDAO.create(user);
        return user;
    }

    private User getAnotherUser(){
        User user = new User();
        user.setFullName("userName");
        user.setEmail("userEmail@mail.com");
        user.setPassword("userPassword");
        userDAO.create(user);
        return user;
    }

    private void createManyOrders(User user){
        Date date = new Date();
        Date deliveryDate = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        Order order;
        for(int i = 0; i < 6; i++){
            order = new Order();
            order.setPerson(user.getFullName());
            order.setDocument("Nr.64532-wre" + i);
            order.setAddress("some street 3-" + i);
            order.setPhone("65342145" + i);
            order.setOrderDate(new Date(date.getTime()));
            order.setDeliveryDate(deliveryDate);
            order.setTotal(6543 + i);
            order.setUserId(user.getId());
            orderDAO.create(order);
        }
    }


}
