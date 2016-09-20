package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/shippingProfiles")
public class ShippingProfileController {

    @Autowired
    Notification notification;

    @Autowired
    private ShippingDetailsUtil shippingDetailsUtil;

    @Autowired
    private ShippingProfileService shippingProfileService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView model() {
        ModelAndView model = new ModelAndView("/shippingProfiles");
        try {
            model.addAllObjects(shippingProfileService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            return SpringPathResolver.redirectTo(LoginController.class);
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return SpringPathResolver.redirectTo(ShippingProfileController.class);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam Map<String, String> param) {
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
            return new ModelAndView("/notification");
        } catch (DBException e) {
            return new ModelAndView("redirect:error");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return SpringPathResolver.redirectTo(ShippingProfileController.class);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("profileId") String resourceId) {
        try {
            if (resourceId != null) {
                Long id = Long.valueOf(resourceId);
                shippingProfileService.delete(id);
            }
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return SpringPathResolver.redirectTo(ShippingProfileController.class);
    }

}

