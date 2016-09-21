package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    @Autowired
    SpecialSaleOffer specialSaleOffer;
    @Autowired
    UserProvider userProvider;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView model() {

        if (!userProvider.authorized())
            return SpringPathResolver.redirectTo(LoginController.class);
        ModelAndView mov = new ModelAndView("contact");
        mov.addAllObjects(templateService.model());
        return mov;
    }

}
