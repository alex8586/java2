package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.database.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView model() {
        ModelAndView mov = new ModelAndView("contact");
        mov.addObject("categories", categoryDAO.getAll());
        mov.addAllObjects(templateService.model());
        mov.addAllObjects(cartService.model());
        return mov;
    }

}
