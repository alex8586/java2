package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ContactController extends MVCController {

    private CategoryDAOImpl categoryDAO;
    private ProductDAOImpl productDAO;

    public ContactController(CategoryDAOImpl categoryDAO, ProductDAOImpl productDAO){
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

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
