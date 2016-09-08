package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.ShippingProfileService;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.ShippingProfile;
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
        ShippingProfile shippingProfile = buildShippingProfileFromRequest(request);
        try {
            shippingProfileService.delete(shippingProfile);
        } catch (ServiceException e) {
            error.setError(e.getMessage());
        }
        return redirectTo(ShippingProfileController.class);
    }

    private ShippingProfile buildShippingProfileFromRequest(HttpServletRequest request) {
        ShippingProfile shippingProfile = new ShippingProfile();

        String param = request.getParameter("profileId");
        if (param != null && !param.isEmpty()) {
            shippingProfile.setId(Long.valueOf(param));
        }
        shippingProfile.setAddress(request.getParameter("address"));
        shippingProfile.setPerson(request.getParameter("person"));
        shippingProfile.setPhone(request.getParameter("phone"));
        shippingProfile.setDocument(request.getParameter("document"));

        return shippingProfile;
    }

}
