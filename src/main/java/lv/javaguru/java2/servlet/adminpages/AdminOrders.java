package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.AdminOrdersService;
import lv.javaguru.java2.businesslogic.validators.AccessDeniedValidator;
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
    @Autowired
    private AccessDeniedValidator accessDeniedValidator;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request){
        if(accessDeniedValidator.isDenied()){
            return new ModelAndView("/access_denied");
        }
        ModelAndView model = new ModelAndView();
        model.addAllObjects(adminOrdersService.getListOrders());
        model.setViewName("/admin_order");
        return model;
    }

    @RequestMapping(value = "/orders/accept", method = RequestMethod.POST)
    public String accept(@RequestParam("orderId") long id){
        adminOrdersService.acceptOrder(id);
        return "redirect:/orders";
    }

}
