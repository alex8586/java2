package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class ContactController extends MVCController {

    @Autowired
    @Qualifier("JDBC_CategoryDAO")
    private CategoryDAO categoryDAO;
    @Autowired
    @Qualifier("JDBC_ProductDAO")
    private ProductDAO productDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> contactData = new HashMap<String, Object>();

        String imgPath;
        int bannerId;
        List<Product> productList = productDAO.getAll();
        if (productList.size() == 0) {
            imgPath = "miskaweb/img/default.jpg";
        } else {
            Random random = new Random();
            bannerId = random.nextInt(productList.size());
            imgPath = productList.get(bannerId).getImgUrl();
        }

        contactData.put("imgPath", imgPath);
        contactData.put("categories" , categoryDAO.getAll());

        if (request.getSession().getAttribute("user") == null) {
            return new MVCModel(contactData, "/contact.jsp");
        }

        User user = (User) request.getSession().getAttribute("user");
        contactData.put("user", user);

        return new MVCModel(contactData, "/contact.jsp");
    }

}
