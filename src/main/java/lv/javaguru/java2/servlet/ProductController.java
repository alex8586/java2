package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ProductController extends MVCController {

    private static final String UNABLE_TO_PROCESS_REQUEST = "Error. Unable to find product";

    @Autowired
    private ProductService productService;
    @Autowired
    private Notification notification;

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
}
