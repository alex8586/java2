package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.AdminOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminOrders {

    @Autowired
    private AdminOrdersService adminOrdersService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request){
        ModelAndView model = new ModelAndView("/admin_order");
        model.addAllObjects(adminOrdersService.getListOrders());
        return model;
    }

    @RequestMapping(value = "/orders/accept", method = RequestMethod.POST)
    public String accept(@RequestParam("orderId") long id){
        adminOrdersService.acceptOrder(id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/sortBy/inProgress")
    public ModelAndView sort(HttpServletRequest request){
        ModelAndView model = new ModelAndView("/admin_order");
        model.addAllObjects(adminOrdersService.getListOrdersSortByStatus());
        return model;
    }
}
