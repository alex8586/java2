package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/shippingProfiles")
public class ShippingProfileController {

    @Autowired
    Notification notification;

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
    public String save(@ModelAttribute ShippingDetails shippingDetails) {
        try {
            shippingProfileService.save(shippingDetails);
        } catch (NullPointerException e) {
            return "redirect:error";
        } catch (DBException e) {
            return "redirect:error";
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return "redirect:shippingProfiles";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("profileId") long resourceId) {
        try {
            shippingProfileService.delete(resourceId);
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return "redirect:shippingProfiles";
    }

}

