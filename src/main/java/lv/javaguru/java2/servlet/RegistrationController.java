package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.UserRegistrationService;
import lv.javaguru.java2.businesslogic.serviceexception.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationController extends MVCController {

    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    Error error;
    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {

        if (!userRegistrationService.allowRegistration()) {
            return redirectTo(FrontPageController.class);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (error.isError())
            map.put("registrationError", error.getError());

        Product product = specialSaleOffer.getOffer();
        map.put("saleOffer", product);
        return new MVCModel(map,"/registration.jsp");
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {

        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            userRegistrationService.register(name, email, password);
            return redirectTo(LoginController.class);
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return redirectTo(RegistrationController.class);
        } catch (DBException e) {
            return new MVCModel("/error");
        }
    }
}
