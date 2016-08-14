package lv.javaguru.java2.servlet;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FrontPageController extends MVCController{

    private CategoryDAOImpl categoryDAO;
    public FrontPageController(CategoryDAOImpl categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String,Object> frontPageData = new HashMap<String,Object>();
        try {
            frontPageData.put("user" , request.getSession().getAttribute("user"));
            frontPageData.put("categories" , categoryDAO.getAll());
            frontPageData.put("products" , null);
        }
        catch (DBException dbe){
        }
        return new MVCModel(frontPageData, "/frontpage.jsp");
    }
}
