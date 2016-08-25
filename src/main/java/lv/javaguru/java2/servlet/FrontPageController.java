package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class FrontPageController extends MVCController{

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String,Object> frontPageData = new HashMap<String,Object>();
        try {
            frontPageData.put("user" , request.getSession().getAttribute("user"));
            frontPageData.put("categories" , categoryDAO.getAll());
            frontPageData.put("products" , productDAO.getAll());
        }
        catch (DBException dbe){
        }
        return new MVCModel(frontPageData, "/frontpage.jsp");
    }
}
