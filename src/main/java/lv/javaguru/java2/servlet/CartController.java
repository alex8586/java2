package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.CartProvider;
import lv.javaguru.java2.businesslogic.CartService;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CartController extends MVCController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartProvider cartProvider;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        Cart cart = cartService.addToCart(id);
        long cartPrice = cart.getTotalPrice(cart);

        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("cartPrice", cartPrice);
        return redirectTo(FrontPageController.class);
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        if (request.getParameter("remove") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            long cartPrice = cartService.removeProduct(productId);
            request.getSession().setAttribute("cartPrice", cartPrice);
            return redirectTo(FrontPageController.class);
        }
        if (request.getParameter("add") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            long cartPrice = cartService.addProduct(productId);
            request.getSession().setAttribute("cartPrice", cartPrice);
            return redirectTo(FrontPageController.class);
        }
        if (request.getParameter("buy") != null) {
            if(cartProvider.isEmpty()){
                return redirectTo(FrontPageController.class);
            }
            return redirectTo(CheckoutController.class);
        }
        return redirectTo(FrontPageController.class);
    }
}
