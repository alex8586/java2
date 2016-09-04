package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.CartService;
import lv.javaguru.java2.domain.Cart;
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
        Cart cart = cartService.addToCart(id);
        long cartPrice = cart.getTotalPrice(cart);

        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("cartPrice", cartPrice);
        return new MVCModel("/index");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        if (request.getParameter("remove") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            long cartPrice = cartService.deleteProduct(productId);
            request.getSession().setAttribute("cartPrice", cartPrice);
            return new MVCModel("/index");
        }
        if (request.getParameter("add") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            long cartPrice = cartService.addProduct(productId);
            request.getSession().setAttribute("cartPrice", cartPrice);
            return new MVCModel("/index");
        }
        if (request.getParameter("buy") != null) {
            return new MVCModel("/index");
        }
        return new MVCModel("/index");
    }
}