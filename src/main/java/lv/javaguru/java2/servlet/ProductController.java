package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.ProductProvider;
import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.StockValidationService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
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
    private static final String QUANTITY_MUST_BE_NUMBERS = "Quantity must be numbers";

    @Autowired
    @Qualifier("JDBC_ProductDAO")
    private ProductDAOImpl productDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private Notification notification;
    @Autowired
    private CartService cartService;
    @Autowired
    private StockValidationService stockValidationService;
    @Autowired
    private CartProvider cartProvider;

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {
        try {
            Long id = idFrom(request.getParameter("id"));
            Map<String, Object> map;
            map = productService.getById(id, request.getRemoteAddr());
            return new MVCModel(map, "/product.jsp");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        } catch (NumberFormatException e) {
            notification.setError(UNABLE_TO_PROCESS_REQUEST);
        } catch (NullPointerException e) {
            notification.setError(UNABLE_TO_PROCESS_REQUEST);
        } catch (DBException e) {
            notification.setError(e.getMessage());
        }
        return redirectTo(FrontPageController.class);
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (request.getParameter("addToCart") != null) {
            long productId = idFrom(request.getParameter("productId"));

            int quantity;
            try {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            } catch (NumberFormatException e) {
                map.put("error", QUANTITY_MUST_BE_NUMBERS);
                return new MVCModel(map, "/product.jsp");
            }
            if (!stockValidationService.isValid(quantity, productId)) {
                map.put("error", QUANTITY_MORE_THAN_STOCK);
                return new MVCModel(map, "/product.jsp");
            }else if(quantity <= 0){
                map.put("error", NOT_CORRECT_QUANTITY);
                return new MVCModel(map, "/product.jsp");
            }

            Cart cart = cartProvider.getCart();
            cartService.addProducts(cart, productId, quantity);
        }
        return redirectTo(ProductController.class);
    }
}
