package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.UserDAO;
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
public class LoginController extends MVCController{

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String WRONG_EMAIL = "user with such email not found";
    private final String WRONG_PASSWORD = "wrong password";

    @Autowired
    @Qualifier("JDBC_UserDAO")
    private UserDAO userDAO;
    @Autowired
    @Qualifier("JDBC_ProductDAO")
    private ProductDAO productDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        String error = null;
        if(request.getSession().getAttribute("loginError") != null) {
             error = (String) request.getSession().getAttribute("loginError");
             request.getSession().removeAttribute("loginError");
        } else if (request.getSession().getAttribute("user") != null) {
            return new MVCModel("/index");
        }

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

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loginError" , error);
        map.put("imgPath", imgPath);
        return new MVCModel(map,"/login.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email.isEmpty() || password.isEmpty()){
            request.getSession().setAttribute("loginError" , EMPTY_FIELDS);
            return new MVCModel("/login");
        }

        try {
            User user = userDAO.getByEmail(email);
            String error = null;
            if(user == null){
                error = WRONG_EMAIL;
            }
            else if(!user.getPassword().equals(password)) {
                error = WRONG_PASSWORD;
            }
            if(error != null){
                request.getSession().setAttribute("loginError" , error);
                return new MVCModel("/login");
            }
            else{
                request.getSession().setAttribute("user" , user);
                return new MVCModel("/profile");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new MVCModel("/login");

    }
}
