package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class ProfileCartController extends MVCController {

    private ProductDAOImpl productDAO;

    public ProfileCartController(ProductDAOImpl productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return new MVCModel("/index");
        }

        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();

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

        map.put("imgPath", imgPath);
        map.put("user", user);

        if (request.getSession().getAttribute("profileError") != null) {
            String error = (String) request.getSession().getAttribute("profileError");
            request.getSession().removeAttribute("profileError");

            map.put("profileError", error);
            map.put("user", user);
            return new MVCModel(map, "/profile.jsp");
        }
        return new MVCModel(map, "/profile_cart.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return new MVCModel("/index");
    }

}
