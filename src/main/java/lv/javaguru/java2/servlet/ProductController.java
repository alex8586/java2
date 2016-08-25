package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by algis on 16.17.8.
 */

@Component
public class ProductController extends MVCController {

    private static final String UNABLE_TO_PROCESS_REQUEST = "Error. Unable to find product";
    private static final String WRONG_PRODUCT_ID = "Error. No item with such id";

    ProductDAOImpl productDAO = new ProductDAOImpl();

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();

        String param = request.getParameter("id");
        if (param == null || param.isEmpty()) {
            map.put("error", UNABLE_TO_PROCESS_REQUEST);
            return new MVCModel(map, "/product.jsp");
        }
        Long id = Long.valueOf(param);
        Product product = productDAO.getById(id);
        if (product == null) {
            map.put("error", WRONG_PRODUCT_ID);
            return new MVCModel(map, "/product.jsp");
        }
        map.put("product", product);
        return new MVCModel(map, "/product.jsp");
    }
}
