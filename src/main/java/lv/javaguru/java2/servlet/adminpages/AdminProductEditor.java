package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.ProductEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminProductEditor {

    @Autowired
    private ProductEditorService productEditorService;

    @RequestMapping(value = "/productEditor", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request){
        ModelAndView model = new ModelAndView("/admin_productEditor");
        model.addAllObjects(productEditorService.getProductList());
        return model;
    }

    @RequestMapping(value = "product/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("productId") long productId){
        System.out.println("=============== productId = " + productId);
        productEditorService.delete(productId);
        return "redirect:productEditor";
    }
}
