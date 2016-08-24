package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.successor.ShippingProfileDAOImpl;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by algis on 16.21.8.
 */
public class ShippingProfileDeleteController extends MVCController {

    private final String UNABLE_TO_LOCATE_RESOURCE = "Unable to locate resource for removal";
    private ShippingProfileDAOImpl shippingProfileDAO;

    public ShippingProfileDeleteController(ShippingProfileDAOImpl shippingProfileDAO) {
        this.shippingProfileDAO = shippingProfileDAO;
    }

    public MVCModel executePost(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new MVCModel("/login");
        }
        String param = request.getParameter("profileId");
        long id = 0;
        if (param != null && !param.isEmpty()) {
            id = Long.valueOf(param);
            ShippingProfile shippingProfile = shippingProfileDAO.getById(id);
            if (shippingProfile != null) {
                if (shippingProfile.getUserId() == user.getId()) {
                    shippingProfileDAO.delete(shippingProfile);
                    return new MVCModel("/profile/shippingProfile");
                } else
                    throw new IllegalStateException("Unable to access resource");
            }
        }
        request.getSession().setAttribute("profileError", UNABLE_TO_LOCATE_RESOURCE);
        return new MVCModel("/profile/shippingProfile");
    }
}
