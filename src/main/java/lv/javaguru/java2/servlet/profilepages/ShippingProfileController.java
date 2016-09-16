package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ShippingProfileController {

    @Autowired
    Notification notification;

    @Autowired
    private ShippingDetailsUtil shippingDetailsUtil;

    @Autowired
    private ShippingProfileService shippingProfileService;

    @RequestMapping(value = "/shippingProfiles", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/shippingProfiles");
        try {
            model.addAllObjects(shippingProfileService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            return redirectTo(LoginController.class);
            //return new ModelAndView("/login");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            //return redirectTo(ShippingProfileController.class);
            return new ModelAndView("/shippingProfiles");
        }
    }

    @RequestMapping(value = "/shippingProfiles", method = RequestMethod.POST)
    public String executePost(@RequestParam Map<String, String> param, HttpServletRequest request) {
        try {
            ShippingDetails shippingDetails =
                    shippingDetailsUtil.build(
                            param.get("profileId"),
                            param.get("person"),
                            param.get("address"),
                            param.get("phone"),
                            param.get("document"));
            shippingProfileService.save(shippingDetails);
        } catch (NullPointerException e) {
            return new MVCModel("/notification");
        } catch (DBException e) {
            return new MVCModel("/notification");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return "redirect:shippingProfiles";
    }

    @RequestMapping(value = "/deleteShippingProfiles", method = RequestMethod.POST)
    public String executePost(@RequestParam("profileId") String resourceId, HttpServletRequest request) {
        try {
            if (resourceId != null) {
                Long id = Long.valueOf(resourceId);
                shippingProfileService.delete(id);
            }
        } catch (ServiceException e) {
            error.setError(e.getMessage());
        }
        return "redirect:shippingProfiles";
    }

}

