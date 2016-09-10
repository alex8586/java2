package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.ShippingProfileService;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ShippingProfileDeleteController extends MVCController {

    @Autowired
    Error error;
    @Autowired
    private ShippingProfileService shippingProfileService;

    public MVCModel executePost(HttpServletRequest request) {
        try {
            String resourceId = request.getParameter("profileId");
            if (resourceId != null) {
                Long id = Long.valueOf(resourceId);
                shippingProfileService.delete(id);
            }
        } catch (ServiceException e) {
            error.setError(e.getMessage());
        }
        return redirectTo(ShippingProfileController.class);
    }
}
