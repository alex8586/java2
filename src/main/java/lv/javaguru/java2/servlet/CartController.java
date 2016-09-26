package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/cart")
public class CartController  {

    private final static String WRONG_NUMBER_FORMAT = "Quantity must be numbers";
    @Autowired
    private CartService cartService;
    @Autowired
    private CartProvider cartProvider;
    @Autowired
    private Notification notification;

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("productId") long id) {
        Cart cart = cartProvider.getCart();
        cartService.addProduct(cart, id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/addQuantity", method = RequestMethod.POST)
    public String addQuantity(@RequestParam ("productId") long productId,
                              @RequestParam(value = "quantity") String quantity) {
        int amount;
        try {
             amount = Integer.parseInt(quantity);
        }catch (NumberFormatException e){
            notification.setError(WRONG_NUMBER_FORMAT);
            return "redirect:/product/" + productId;
        }
        if (amount != 0 && amount > 0) {
            Cart cart = cartProvider.getCart();
            cartService.addProducts(cart, productId, amount);
        }

        return "redirect:/product/" + productId;
    }

    @RequestMapping(value = "/addRemove", method = RequestMethod.POST)
    public String addRemove(@RequestParam("productId") long productId, HttpServletRequest request) {
        Cart cart = cartProvider.getCart();
        if (request.getParameter("remove") != null) {
            cartService.removeProduct(cart, productId);
        } else if (request.getParameter("add") != null) {
            cartService.addProduct(cart, productId);
        }
        return "redirect:" + request.getHeader(HttpHeaders.REFERER);
    }

}
