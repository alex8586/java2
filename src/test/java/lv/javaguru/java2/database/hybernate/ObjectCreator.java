package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.*;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class ObjectCreator {

    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;

    @Qualifier("ORM_UserDAO")
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StockDAO stockDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired OrderLineDAO orderLineDAO;

    private Random random = new Random();
    private Date today = new Date();

    long createCategory(){
        Category category = new Category();
        category.setName("category");
        return categoryDAO.create(category);
    }

    long createProduct(long categoryId){
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setName("product");
        product.setDescription("description");
        product.setPrice(123);
        product.setImgUrl("url");
        return productDAO.create(product);
    }

    long createUser(){
        User user = new User();
        user.setEmail("name@server" + random.nextInt(100000) + ".com");
        user.setFullName("Name Surname " + random.nextInt(100000));
        user.setPassword("password" + random.nextInt(100000));
        return userDAO.create(user);
    }

    long createReview(){
        Review review = new Review();
        review.setUserId(createUser());
        review.setProductId(createProduct(createCategory()));
        review.setUserName("userName" + random.nextInt(100000));
        review.setComment("review" + random.nextInt(100000));
        review.setDate(new Date());
        return reviewDAO.create(review);
    }

    Stock createStock(){
        Stock stock = new Stock();
        stock.setProductId(createProduct(createCategory()));
        stock.setQuantity(random.nextInt(100));
        stock.setExpireDate(new Date(today.getTime() + (1000*60*60*random.nextInt(60))));
        long id = stockDAO.create(stock);
        return stockDAO.getById(id);
    }

    Order createOrder(boolean status){
        Order order = new Order();
        order.setPerson("user" + random.nextInt(1000));
        order.setDocument("Nr.2680307" + random.nextInt(1000));
        order.setAddress("city, street " + random.nextInt(1000));
        order.setPhone("2349834" + random.nextInt(1000));
        order.setOrderDate(new Date(today.getTime()));
        order.setDeliveryDate(new Date(today.getTime() + 1000*60*60*random.nextInt(60)));
        order.setTotal(random.nextInt(200));
        order.setUserId(createUser());
        order.setStatus(status);
        order.setSecurityKey("736hdg47" + random.nextInt(10000));
        long id = orderDAO.create(order);
        return orderDAO.getById(id);
    }

    Order createOrderWithTwoOrderLineStatusFalse(){
        Order order = createOrder(false);
        Stock stock = createStock();
        Product product = productDAO.getById(stock.getProductId());

        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        orderLine.setProductId(product.getId());
        orderLine.setName(product.getName());
        orderLine.setDescription(product.getDescription());
        orderLine.setPrice(product.getPrice());
        orderLine.setQuantity(stock.getQuantity());
        orderLine.setExpireDate(stock.getExpireDate());
        orderLineDAO.create(orderLine);

        OrderLine other = new OrderLine();
        other.setOrder(order);
        other.setProductId(product.getId());
        other.setName(product.getName());
        other.setDescription(product.getDescription());
        other.setPrice(product.getPrice());
        other.setQuantity(stock.getQuantity());
        other.setExpireDate(stock.getExpireDate());
        orderLineDAO.create(other);

        return order;
    }
}
