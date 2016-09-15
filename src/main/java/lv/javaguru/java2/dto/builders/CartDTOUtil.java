package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.CartDTO;
import lv.javaguru.java2.dto.ProductCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CartDTOUtil {

    @Autowired
    ProductCardUtil productCardUtil;

    public CartDTO build(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        List<ProductCard> productCards = new ArrayList<>();
        for (Map.Entry<Product, Integer> cartLine : cart.getAll().entrySet()) {
            productCards.add(productCardUtil.build(cartLine.getKey(), cartLine.getValue()));
        }
        cartDTO.setProductCards(productCards);
        cartDTO.setCartCheckSum(cart.getHashCode());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        return cartDTO;
    }
}
