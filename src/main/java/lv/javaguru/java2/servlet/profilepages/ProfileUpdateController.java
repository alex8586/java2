package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.ProfileUpdateService;
import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileUpdateController extends MVCController {

    @Autowired
    UserProfileUtil userProfileUtil;

    @Autowired
    ProfileUpdateService profileUpdateService;

    @Autowired
    Error error;

    @Autowired
    private SpecialSaleOffer specialSaleOffer;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return redirectTo(FrontPageController.class);
        }

        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();
        Product product = specialSaleOffer.getOffer();

        if (request.getSession().getAttribute("profileError") != null) {
            String error = (String) request.getSession().getAttribute("profileError");
            request.getSession().removeAttribute("profileError");

            map.put("saleOffer", product);
            map.put("profileError", error);
            map.put("user", user);
            return new MVCModel(map, "/profile_update.jsp");
        }

        map.put("saleOffer", product);
        map.put("user", user);

        return new MVCModel(map, "/profile_update.jsp");
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {

        try {
            UserProfile userProfile = userProfileUtil
                    .build(request.getParameter("fullName"),
                            request.getParameter("email"),
                            request.getParameter("password"),
                            request.getParameter("repeatPassword"));
            profileUpdateService.update(userProfile);
        } catch (NullPointerException e) {
            return new MVCModel("/error");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return redirectTo(ProfileUpdateController.class);
        } catch (DBException e) {
            return new MVCModel("/error");
        }
        return redirectTo(ProfileUpdateController.class);
    }
}