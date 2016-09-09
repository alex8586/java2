package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class OrderLineORMDAOImplTest extends CrudDAOTest<OrderLine, OrderLineORMDAOImpl> {

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;
    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;
    @Qualifier("ORM_UserDAO")
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDAO orderDAO;
    private Random random = new Random();
    private Category category;
    private Product product;
    private User user;
    private Order order;

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

        user = new User();
        user.setFullName("name");
        user.setEmail("email@mail.com");
        user.setPassword("password");
        userDAO.create(user);

        order = new Order();
        order.setPerson(user.getFullName());
        order.setDocument("Nr.64532-wre");
        order.setAddress("some street 34");
        order.setPhone("65342145");
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
        order.setTotal(654);
        order.setUserId(user.getId());
        orderDAO.create(order);
        super.before();
    }

    @Override
    protected void fillRecordWithData(OrderLine orderLine) {
        orderLine.setOrderId(order.getId());
        orderLine.setProductId(product.getId());
        orderLine.setName("name");
        orderLine.setDescription("desc");
        orderLine.setPrice(123);
        orderLine.setQuantity(123);
        orderLine.setExpireDate(new Date());
    }

    @Override
    protected void makeChangesInRecord(OrderLine orderLine) {
        orderLine.setName("changedName");
        orderLine.setDescription("changes");
        orderLine.setPrice(456);
        orderLine.setQuantity(456);
        orderLine.setExpireDate(new Date());
    }
}
