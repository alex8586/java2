package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContactController extends MVCController {

    @Autowired
    SpecialSaleOffer specialSaleOffer;
    @Autowired
    @Qualifier("JDBC_CategoryDAO")
    private CategoryDAO categoryDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> contactData = new HashMap<String, Object>();

        contactData.put("categories" , categoryDAO.getAll());
        Product product = specialSaleOffer.getOffer();
        contactData.put("saleOffer", product );

        if (request.getSession().getAttribute("user") == null) {
            return new MVCModel(contactData, "/contact.jsp");
        }

        User user = (User) request.getSession().getAttribute("user");
        contactData.put("user", user);

        return new MVCModel(contactData, "/contact.jsp");
    }

}
