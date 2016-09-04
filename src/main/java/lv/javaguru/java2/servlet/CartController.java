package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CartController extends MVCController {

    @Autowired
    CartService cartService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        cartService.addToCart(id);
        return new MVCModel("/index");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        if (request.getParameter("delete") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            cartService.deleteProduct(productId);
            return new MVCModel("/index");
        }
        if (request.getParameter("add") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            cartService.addProduct(productId);
            return new MVCModel("/index");
        }
        if (request.getParameter("buy") != null) {
            return new MVCModel("/index");
        }
        return new MVCModel("/index");
    }
}
