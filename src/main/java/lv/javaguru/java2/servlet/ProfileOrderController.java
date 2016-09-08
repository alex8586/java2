package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ProfileOrderController extends MVCController {

    @Autowired
    private OrderService orderService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return redirectTo(FrontPageController.class);
        }

        Map<String, Object> map = orderService.getOrder(Long.parseLong(request.getParameter("id")));

        return new MVCModel(map, "/profile_order.jsp");
    }
}
