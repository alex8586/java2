package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.UserRegistrationService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class RegistrationController extends MVCController {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String UNEXPECTED_ERROR = "Oops, something went wrong";
    private final String USER_ALREADY_EXISTS = "User already exists";
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    private ProductDAO productDAO;
    
    @Override
    protected MVCModel executeGet(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String error = (String) request.getSession().getAttribute("registrationError");
        request.getSession().removeAttribute("registrationError");

        if (user != null)
            return new MVCModel("/index");

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
        try {
            userRegistrationService.register(name, email, password);
            return new MVCModel("/login");
        } catch (ServiceException e) {
            request.getSession().setAttribute("registrationError", e.getMessage());
            return new MVCModel("/register");
        } catch (DBException e) {
            return new MVCModel("/error");
        }
    }
}
