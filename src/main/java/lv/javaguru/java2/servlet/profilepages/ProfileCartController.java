package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.SpecialSaleOffer;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileCartController extends MVCController {

    @Autowired
    private SpecialSaleOffer specialSaleOffer;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return redirectTo(FrontPageController.class);
        }

        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();
        Product product = specialSaleOffer.getOffer();

        map.put("saleOffer", product);
        map.put("user", user);

        return new MVCModel(map, "/profile_cart.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return redirectTo(FrontPageController.class);
    }

}
