package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.ShippingProfileService;
import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.UserProvider;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShippingProfileController extends MVCController {

    @Autowired
    ShippingProfileService shippingProfileService;
    @Autowired
    Error error;
    @Autowired
    UserProvider userProvider;
    @Autowired
    private SpecialSaleOffer specialSaleOffer;

    public MVCModel executeGet(HttpServletRequest request) {
        List<ShippingProfile> shippingProfiles = null;
        try {
            shippingProfiles = shippingProfileService.list();
        } catch (IllegalRequestException e) {
            return redirectTo(LoginController.class);
        } catch (ServiceException e) {
            error.setError(e.getMessage());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shippingProfiles", shippingProfiles);

        Product product = specialSaleOffer.getOffer();
        map.put("saleOffer", product);
        map.put("user", userProvider.getUser());
        return new MVCModel(map, "/shippingProfiles.jsp");
    }

    public MVCModel executePost(HttpServletRequest request) {
        ShippingProfile shippingProfile = buildShippingProfileFromRequest(request);
        try {
            shippingProfileService.save(shippingProfile);
        } catch (Exception e) {
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

