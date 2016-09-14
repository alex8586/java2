package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.profilepages.ProfileOrderService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ProfileHistoryOrdersController extends MVCController {

    @Autowired
    private ProfileOrderService profileOrderService;

    @Autowired
    private Error error;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        try {
            Map<String, Object> map = profileOrderService.model();
            return new MVCModel(map, "/profile_history.jsp");
        } catch (UnauthorizedAccessException e) {
            return redirectTo(LoginController.class);
        } catch (ServiceException e) {
            error.isError();
            return redirectTo(ProfileHistoryOrdersController.class);
        }
    }
}
