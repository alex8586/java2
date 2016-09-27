package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.StockEditorService;
import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminStockEditor {

    @Autowired
    private StockEditorService stockEditorService;
    @Autowired
    private Notification notification;

    @RequestMapping(value = "/stockEditor", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/admin_stockEditor");
        model.addAllObjects(stockEditorService.getStockList());
        return model;
    }

    @RequestMapping(value = "/stockEditor/edit", method = RequestMethod.POST)
    public String edit(@RequestParam("id") long stockId,
                       @RequestParam("quantity") String quantity,
                       @RequestParam("expireDate") String expireDate) {
        try {
            stockEditorService.updateStock(stockId, quantity, expireDate);
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return "redirect:/stockEditor";
    }
}
