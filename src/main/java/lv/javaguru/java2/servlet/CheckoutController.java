package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class CheckoutController extends MVCController {

    @Autowired
    private CheckoutService checkoutService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> data = checkoutService.serve();
        return new MVCModel(data, "/checkout.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return null;
    }
}
