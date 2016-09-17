package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CartService;
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
        long id = idFrom(request.getParameter("id"));
        Cart cart = cartProvider.getCart();
        cartService.addProduct(cart, id);
        long cartPrice = cart.getTotalPrice(cart);
        request.getSession().setAttribute("cart", cart);
        return redirectTo(FrontPageController.class);
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        Cart cart = cartProvider.getCart();
        long productId = Long.parseLong(request.getParameter("productId"));
        int quantity = 1;
        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
        if (request.getParameter("remove") != null) {
            cartService.removeProduct(cart, productId);
        } else if (request.getParameter("add") != null) {
            cartService.addProducts(cart, productId, quantity);
        }
        return redirectTo(FrontPageController.class);
    }
}
