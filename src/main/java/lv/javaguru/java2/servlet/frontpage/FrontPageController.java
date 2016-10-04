package lv.javaguru.java2.servlet.frontpage;

import lv.javaguru.java2.businesslogic.frontpage.FrontPageService;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class FrontPageController {

    @Autowired
    FrontPageService frontPageService;

    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView model(@SessionAttribute(value = "currentCategory", required = false) Category category,
                              @SessionAttribute(value = "sortingStrategy", required = false) String sortBy) {
        ModelAndView model = new ModelAndView("frontpage");
        Map<String, Object> frontPageData = frontPageService.model(category, sortBy);
        model.addAllObjects(frontPageData);
        return model;
    }

    @RequestMapping("/category/{id}")
    public ModelAndView chooseCategory(@PathVariable("id") long id, HttpServletRequest request) {
        Category category = categoryDAO.getById(id);
        if (category != null) {
            if (category.getFatherId() == 0)
                request.getSession().removeAttribute("currentCategory");
            request.getSession().setAttribute("currentCategory", category);
        }
        return SpringPathResolver.redirectTo(FrontPageController.class);
    }

    @RequestMapping("/all")
    public ModelAndView all(HttpServletRequest request) {
        request.getSession().removeAttribute("currentCategory");
        request.getSession().removeAttribute("sortingStrategy");
        return SpringPathResolver.redirectTo(FrontPageController.class);
    }

    @RequestMapping("/sortBy/{sortingStrategy}")
    public ModelAndView sortBy(@PathVariable("sortingStrategy") String sortBy, HttpServletRequest request) {
        request.getSession().setAttribute("sortingStrategy", sortBy);
        return SpringPathResolver.redirectTo(FrontPageController.class);
    }
}
