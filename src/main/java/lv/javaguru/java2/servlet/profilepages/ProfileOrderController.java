package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.profilepages.ProfileOrderService;
import lv.javaguru.java2.businesslogic.validators.ProfileOrderValidationService;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ProfileOrderController extends MVCController {

    @Autowired
    private ProfileOrderService profileOrderService;
    @Autowired
    private ProfileOrderValidationService profileOrderValidationService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return redirectTo(FrontPageController.class);
        }
        long orderId = Long.parseLong(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        if(!profileOrderValidationService.isValid(orderId, user.getId())){
            return redirectTo(FrontPageController.class);
        }

        Map<String, Object> map = profileOrderService.getOrder(orderId);

        return new MVCModel(map, "/profile_order.jsp");
    }
}
