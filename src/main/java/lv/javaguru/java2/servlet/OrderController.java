package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.PendingOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class OrderController extends MVCController {

    @Autowired
    private PendingOrderServiceImpl orderService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> data = orderService.serve();
        return new MVCModel(data, "/order.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return null;
    }
}
