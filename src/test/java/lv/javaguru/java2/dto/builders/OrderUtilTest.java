package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.businesslogic.validators.LockedResourceAccessService;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.domain.order.OrderLine;
import lv.javaguru.java2.dto.ShippingDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class OrderUtilTest {

    Order order;
    OrderUtil orderUtil;

    @Before
    public void before() {
        order = new Order();
        orderUtil = new OrderUtil();
    }

    @Test
    public void testBuildWithUser() {
        User user = new User();
        user.setId(123);
        orderUtil.build(user, order);
        assertEquals(user.getId(), order.getUserId());
    }

    @Test
    public void testBuildWithoutUser() {
        User user = null;
        orderUtil.build(user, order);
        assertEquals(0, order.getUserId());
    }

    @Test
    public void testBuildWithShippingDetails() {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setDocument("doc");
        shippingDetails.setPerson("person");
        shippingDetails.setPhone("phone");
        shippingDetails.setAddress("address");
        shippingDetails.setId(1234);
        orderUtil.build(shippingDetails, order);
        assertEquals(shippingDetails.getDocument(), order.getDocument());
        assertEquals(shippingDetails.getPhone(), order.getPhone());
        assertEquals(shippingDetails.getPerson(), order.getPerson());
        assertEquals(shippingDetails.getAddress(), order.getAddress());
    }

    @Test
    public void testBuildWithDateDefaults() {
        Date date = new Date(new Date().getTime());
        orderUtil.build(date, order);
        assertEquals("In progress", order.getStatus());
        assertEquals(date, order.getDeliveryDate());
        assertNotNull(order.getOrderDate());
    }

    @Test
    public void testBuildWithLockedResourceService() {
        String secretKey = "secretKey";
        LockedResourceAccessService lockedResourceAccessService = Mockito.mock(LockedResourceAccessService.class);
        Mockito.doReturn(secretKey).when(lockedResourceAccessService).generateKey();
        orderUtil.build(lockedResourceAccessService, order);
        assertEquals(secretKey, order.getSecurityKey());
    }

    @Test
    public void testBuildWithCart() {
        Product product1 = new Product();
        product1.setPrice(2);
        product1.setId(321);
        product1.setName("name");
        product1.setDescription("desc");

        Product product2 = new Product();
        product2.setPrice(3);
        product2.setId(543);
        product2.setName("name2");
        product2.setDescription("desc2");

        Cart cart = new Cart();
        cart.add(product1, 1);
        cart.add(product2, 2);
        orderUtil.build(cart, order);
        assertEquals(8, order.getTotal());
        assertEquals(2, order.getOrderLines().size());

        OrderLine line2 = order.getOrderLines().get(0);
        if (line2.getId() == product1.getId())
            line2 = order.getOrderLines().get(1);

        assertEquals(product2.getPrice(), line2.getPrice());
        assertEquals(product2.getId(), line2.getProductId());
        assertEquals(product2.getName(), line2.getName());
        assertEquals(product2.getDescription(), line2.getDescription());
    }

}
