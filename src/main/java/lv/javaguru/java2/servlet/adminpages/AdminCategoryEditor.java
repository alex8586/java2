package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.CategoryEditorService;
import lv.javaguru.java2.businesslogic.validators.AccessDeniedValidator;
import lv.javaguru.java2.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminCategoryEditor {

    @Autowired
    private CategoryEditorService categoryEditorService;
    @Autowired
    private AccessDeniedValidator accessDeniedValidator;

    @RequestMapping(value = "/categoryEditor", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request){
        if(accessDeniedValidator.isDenied()){
            return new ModelAndView("/access_denied");
        }
        ModelAndView model = new ModelAndView("/admin_categoryEditor");
        model.addAllObjects(categoryEditorService.getCategoryList());
        return model;
    }

    @RequestMapping(value = "category/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("deleteId")long deleteId){
        categoryEditorService.delete(deleteId);
        return "redirect:/categoryEditor";
    }

    @RequestMapping(value = "category/addChild", method = RequestMethod.POST)
    public String addChild(@RequestParam("fatherId") long fatherId) {
        categoryEditorService.addChild(fatherId);
        return "redirect:/categoryEditor";
    }

    @RequestMapping(value = "category/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Category category){
        categoryEditorService.edit(category);
        return "redirect:/categoryEditor";
    }


}
