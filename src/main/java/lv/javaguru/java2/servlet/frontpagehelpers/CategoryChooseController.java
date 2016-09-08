package lv.javaguru.java2.servlet.frontpagehelpers;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.servlet.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CategoryChooseController extends MVCController {

    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        long categoryId = Long.valueOf(request.getParameter("id"));
        Category category = categoryDAO.getById(categoryId);
        if (category != null) {
            if (category.getFather_id() == 0)
                request.getSession().removeAttribute("currentCategory");
            request.getSession().setAttribute("currentCategory", category);
        }
        return redirectTo(FrontPageController.class);
    }

}
