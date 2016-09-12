package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.UserRegistrationService;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationController extends MVCController {

    @Autowired
    UserProfileUtil userProfileUtil;
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
        try {
            UserProfile userProfile = userProfileUtil
                    .build(request.getParameter("fullName"),
                            request.getParameter("email"),
                            request.getParameter("password"),
                            request.getParameter("repeatPassword"));
            userRegistrationService.register(userProfile);
        } catch (NullPointerException e) {
            return new MVCModel("/error");
        } catch (DBException e) {
            return new MVCModel("/error");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return redirectTo(RegistrationController.class);
        }
        return redirectTo(LoginController.class);
    }
}
