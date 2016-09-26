package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.ProductEditorService;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("productId") long productId){
        productEditorService.delete(productId);
        return "redirect:/productEditor";
    }

    @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, @ModelAttribute Product product) {
        System.out.println(request.getParameterMap());

        for (Map.Entry<String, String[]> stringEntry : request.getParameterMap().entrySet()) {
            for (String s : stringEntry.getValue()) {
                System.out.println(stringEntry.getKey() + " " + s);
            }
        }
        System.out.println(product);

        System.out.println(product);
        productEditorService.update(product);
        return "redirect:/productEditor";
    }
}
