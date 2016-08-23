package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RegistrationController extends MVCController {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String UNEXPECTED_ERROR = "Oops, something went wrong";
    private final String USER_ALREADY_EXISTS = "User already exists";

    private UserDAOImpl userDAO;
    private ProductDAOImpl productDAO;

    public RegistrationController(UserDAOImpl userDAO, ProductDAOImpl productDAO) {
        this.userDAO = userDAO;
        this.productDAO = productDAO;
    }

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {
        String error = null;
        if(request.getSession().getAttribute("registrationError") != null) {
            error = (String) request.getSession().getAttribute("registrationError");
            request.getSession().removeAttribute("registrationError");
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
        map.put("registrationError" , error);
        map.put("imgPath", imgPath);
        return new MVCModel(map,"/registration.jsp");
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {

        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            request.getSession().setAttribute("registrationError" , EMPTY_FIELDS);
            return new MVCModel("/register");
        }

        try {
            User userCheckedByEmail = userDAO.getByEmail(email);
            if(userCheckedByEmail!= null){
                request.getSession().setAttribute("registrationError" , USER_ALREADY_EXISTS);
                return new MVCModel("/register");
            }
            User user = new User();
            user.setFullName(name);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.create(user);
            return new MVCModel("/login");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("registrationError" , UNEXPECTED_ERROR);
        return new MVCModel("/register");
    }
}
