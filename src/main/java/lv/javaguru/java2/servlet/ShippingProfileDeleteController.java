package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ShippingProfileDeleteController extends MVCController {

    private final String UNABLE_TO_LOCATE_RESOURCE = "Unable to locate resource for removal";

    @Autowired
    @Qualifier("JDBC_ShippingProfileDAO")
    private ShippingProfileDAO shippingProfileDAO;

    public MVCModel executePost(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return redirectTo(LoginController.class);
        }
        String param = request.getParameter("profileId");
        long id = 0;
        if (param != null && !param.isEmpty()) {
            id = Long.valueOf(param);
            ShippingProfile shippingProfile = shippingProfileDAO.getById(id);
            if (shippingProfile != null) {
                if (shippingProfile.getUserId() == user.getId()) {
                    shippingProfileDAO.delete(shippingProfile);
                    return redirectTo(ShippingProfileController.class);
                } else
                    throw new IllegalStateException("Unable to access resource");
            }
        }
        request.getSession().setAttribute("profileError", UNABLE_TO_LOCATE_RESOURCE);
        return redirectTo(ShippingProfileController.class);
    }
}
