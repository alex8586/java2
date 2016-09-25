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
    @Autowired
    private OrderLineDAO orderLineDAO;

    private Random random = new Random();
    private Category category;
    private Category anotherCategory;
    private Product product;
    private Product anotherProduct;
    private Order order;
    private Order anotherOrder;
    private User user;
    private User anotherUser;
    private Date date = new Date();

    @Before
    public void before() {
        cleaner.cleanDatabase();
        category = new Category();
        category.setName("name");
        categoryDAO.create(category);

        anotherCategory = new Category();
        anotherCategory.setName("new name");
        categoryDAO.create(anotherCategory);

        product = new Product();
        product.setName("name" + random.nextInt(100000));
        product.setDescription("description" + random.nextInt(100000));
        product.setPrice(random.nextInt(100000));
        product.setCategoryId(category.getId());
        product.setImgUrl("imgpath");
        productDAO.create(product);

        anotherProduct = new Product();
        anotherProduct.setName("new name");
        anotherProduct.setDescription("new description");
        anotherProduct.setPrice(654);
        anotherProduct.setCategoryId(anotherCategory.getId());
        anotherProduct.setImgUrl("pathToImage");
        productDAO.create(anotherProduct);

        user = new User();
        user.setFullName("name");
        user.setEmail("email@mail.com");
        user.setPassword("password");
        userDAO.create(user);

        anotherUser = new User();
        anotherUser.setFullName("new name");
        anotherUser.setEmail("new@email.here");
        anotherUser.setPassword("new password");
        userDAO.create(anotherUser);

        order = new Order();
        order.setPerson(user.getFullName());
        order.setDocument("Nr.64532-wre");
        order.setAddress("some street 34");
        order.setPhone("65342145");
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
        order.setTotal(654);
        order.setUserId(user.getId());
        order.setSecurityKey("key");
        orderDAO.create(order);

        anotherOrder = new Order();
        anotherOrder.setPerson(anotherUser.getFullName());
        anotherOrder.setDocument("new document");
        anotherOrder.setAddress("new street 34");
        anotherOrder.setPhone("65342145");
        anotherOrder.setOrderDate(new Date());
        anotherOrder.setDeliveryDate(new Date());
        anotherOrder.setTotal(987);
        anotherOrder.setUserId(anotherUser.getId());
        anotherOrder.setSecurityKey("anotherkey");
        orderDAO.create(anotherOrder);
        super.before();
    }

    @Override
    protected void fillRecordWithData(OrderLine orderLine) {
        orderLine.setOrder(order);
        orderLine.setProductId(product.getId());
        orderLine.setName("name");
        orderLine.setDescription("desc");
        orderLine.setPrice(123);
        orderLine.setQuantity(123);
        orderLine.setExpireDate(new Date());
    }

    @Override
    protected void makeChangesInRecord(OrderLine orderLine) {
        orderLine.setOrder(anotherOrder);
        orderLine.setProductId(anotherProduct.getId());
        orderLine.setName("changedName");
        orderLine.setDescription("changes");
        orderLine.setPrice(456);
        orderLine.setQuantity(456);
        orderLine.setExpireDate(new Date(date.getTime()));
    }

}
