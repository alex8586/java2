package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.UserLoginService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginController extends MVCController{

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String error = (String) request.getSession().getAttribute("loginError");
        request.getSession().removeAttribute("loginError");
        if (user != null)
            return new MVCModel("/index");

        String imgPath = "miskaweb/img/default.jpg";
        Product product = specialSaleOffer.getOffer();
        if (product != null)
            imgPath = product.getImgUrl();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loginError" , error);
        map.put("imgPath", imgPath);
        return new MVCModel(map,"/login.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = userLoginService.login(email, password);
            request.getSession().setAttribute("user", user);
            return new MVCModel("/profile");
        } catch (ServiceException e) {
            request.getSession().setAttribute("loginError", e.getMessage());
            return new MVCModel("/login");
        } catch (DBException e) {
            return new MVCModel("/error");
        }
    }
}
