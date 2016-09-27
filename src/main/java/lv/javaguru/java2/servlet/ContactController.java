package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.helpers.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(name = "ContactController")
public class ContactController {

    @Autowired
    private CategoryTree categoryTree;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET, name = "contactModel", value = "/contact")
    public ModelAndView model() {
        ModelAndView mov = new ModelAndView("contact");
        mov.addObject("rootCategoryNode", categoryTree.getRootNode());
        mov.addAllObjects(templateService.model());
        mov.addAllObjects(cartService.model());
        return mov;
    }
}
