package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.profilepages.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
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
    private ShippingDetailsUtil shippingDetailsUtil;
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
        try {
            ShippingDetails shippingDetails =
                    shippingDetailsUtil.build(
                            request.getParameter("profileId"),
                            request.getParameter("person"),
                            request.getParameter("address"),
                            request.getParameter("phone"),
                            request.getParameter("document"));
            ShippingProfile shippingProfile = shippingProfileService.save(shippingDetails);
        } catch (NullPointerException e) {
            return new MVCModel("/error");
        } catch (DBException e) {
            return new MVCModel("/error");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
        }
        return redirectTo(ShippingProfileController.class);
    }

}

