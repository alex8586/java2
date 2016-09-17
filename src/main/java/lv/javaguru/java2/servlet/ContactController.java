package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ContactController {

    @Autowired
    SpecialSaleOffer specialSaleOffer;
    @Autowired
    UserProvider userProvider;
    @Autowired
    @Qualifier("JDBC_CategoryDAO")
    private CategoryDAO categoryDAO;
    @Autowired
    private TemplateService templateService;


    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (!userProvider.authorized())
            return redirectTo(LoginController.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(templateService.model());
        return new MVCModel(map, "/contact.jsp");
    }

}
