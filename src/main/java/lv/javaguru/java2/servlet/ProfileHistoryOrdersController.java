package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.ProfileOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ProfileHistoryOrdersController extends MVCController {

    @Autowired
    private ProfileOrderService profileOrderService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return redirectTo(FrontPageController.class);
        }
        Map<String, Object> map = profileOrderService.getHistoryOrders();

        return new MVCModel(map, "/profile_history.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return redirectTo(FrontPageController.class);
    }

}
