package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

    private static final String UNABLE_TO_PROCESS_REQUEST = "Error. Unable to find product";

    @Autowired
    private ProductService productService;
    @Autowired
    private Notification notification;

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public ModelAndView productModel(@PathVariable("productId") long productId, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("/product");
        try {
            mav.addAllObjects(productService.getById(productId, request.getRemoteAddr()));
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        } catch (NumberFormatException e) {
            notification.setError(UNABLE_TO_PROCESS_REQUEST);
        } catch (NullPointerException e) {
            notification.setError(UNABLE_TO_PROCESS_REQUEST);
        } catch (DBException e) {
            notification.setError(e.getMessage());
        }
        return mav;
    }
}
