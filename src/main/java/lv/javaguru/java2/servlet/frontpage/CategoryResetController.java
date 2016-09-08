package lv.javaguru.java2.servlet.frontpage;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by algis on 16.8.9.
 */
@Component
public class CategoryResetController extends MVCController {

    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        request.getSession().removeAttribute("currentCategory");
        return redirectTo(FrontPageController.class);
    }

}