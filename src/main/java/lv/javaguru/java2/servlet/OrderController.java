package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class OrderController extends MVCController {

    @Autowired
    private OrderService orderService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> orderData = orderService.getOrder();
        return new MVCModel(orderData, "/order.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return null;
    }
}
