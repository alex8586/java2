package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.product.ProductProvider;
import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.StockValidationService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductController extends MVCController {

    private static final String UNABLE_TO_PROCESS_REQUEST = "Error. Unable to find product";
    private static final String NOT_CORRECT_QUANTITY = "Not correct quantity";
    private static final String QUANTITY_MORE_THAN_STOCK = "Quantity can't be more than stock";

    @Autowired
    @Qualifier("JDBC_ProductDAO")
    ProductDAOImpl productDAO;
    @Autowired
    ProductService productService;
    @Autowired
    Error error;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductProvider productProvider;
    @Autowired
    private StockValidationService stockValidationService;
    @Autowired
    private CartProvider cartProvider;

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String param = request.getParameter("id");
        if(param == null || param.isEmpty()){
            param = String.valueOf(productProvider.getProductId());
        }else if(param == null){
            map.put("error", UNABLE_TO_PROCESS_REQUEST);
            return new MVCModel(map, "/product.jsp");
        }
        try {
            Long id = Long.valueOf(param);
            map = productService.getById(id, request.getRemoteAddr());
        } catch (ServiceException e) {
            map.put("error", e.getMessage());
        } catch (NumberFormatException e) {
            map.put("error", UNABLE_TO_PROCESS_REQUEST);
        } catch (DBException e) {
            map.put("error", e.getMessage());
        }
        return new MVCModel(map, "/product.jsp");
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (request.getParameter("addToCart") != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            productProvider.setProductId(productId);
            request.getSession().setAttribute("productId", productId);

            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (!stockValidationService.isValid(quantity, productId)) {
                map.put("error", QUANTITY_MORE_THAN_STOCK);
                return new MVCModel(map, "/product.jsp");
            }else if(quantity <= 0){
                map.put("error", NOT_CORRECT_QUANTITY);
                return new MVCModel(map, "/product.jsp");
            }

            Cart cart = cartProvider.getCart();
            cartService.addProducts(cart, productId, quantity);
            long cartPrice = cart.getTotalPrice(cart);
            request.getSession().setAttribute("cart", cart);
            request.getSession().setAttribute("cartPrice", cartPrice);
        }
        return redirectTo(ProductController.class);
    }
}
