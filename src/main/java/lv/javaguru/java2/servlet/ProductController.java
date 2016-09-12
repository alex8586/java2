package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*
This dummy controller so we could have roubust product page
 */
@Component
public class ProductController extends MVCController {

    private static final String UNABLE_TO_PROCESS_REQUEST = "Error. Unable to find product";

    @Autowired
    @Qualifier("JDBC_ProductDAO")
    ProductDAOImpl productDAO;

    @Autowired
    ProductService productService;

    @Autowired
    Error error;

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        String param = request.getParameter("id");
        if (param == null || param.isEmpty()) {
            map.put("error", UNABLE_TO_PROCESS_REQUEST);
            return new MVCModel(map, "/product.jsp");
        }
        try {
            Long id = Long.valueOf(param);
            map = productService.getById(id);
        } catch (ServiceException e) {
            map.put("error", e.getMessage());
        } catch (NumberFormatException e) {
            map.put("error", UNABLE_TO_PROCESS_REQUEST);
        } catch (DBException e) {
            map.put("error", e.getMessage());
        }
        return new MVCModel(map, "/product.jsp");
    }
}
