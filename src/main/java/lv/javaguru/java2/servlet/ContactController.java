package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {

    @Autowired
    SpecialSaleOffer specialSaleOffer;
    @Autowired
    @Qualifier("JDBC_CategoryDAO")
    private CategoryDAO categoryDAO;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        model.addObject("categories", categoryDAO.getAll());
        model.addObject("saleOffer", specialSaleOffer.getOffer() );

////        if (request.getSession().getAttribute("user") == null) {
////            return new MVCModel(contactData, "/contact.jsp");
////        }

        User user = (User) request.getSession().getAttribute("user");
        model.addObject("user", user);

        return model;
    }

}
