package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/cart")
public class CartController  {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartProvider cartProvider;

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("productId") long id) {
        Cart cart = cartProvider.getCart();
        cartService.addProduct(cart, id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/addQuantity", method = RequestMethod.POST)
    public String addQuantity(@RequestParam ("productId") long productId,
                              @RequestParam ("quantity") int quantity) {
        Cart cart = cartProvider.getCart();
        cartService.addProducts(cart, productId, quantity);
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
        request.getSession().setAttribute("cart", cart);
        return "redirect:/index";
    }

}
