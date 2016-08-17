package lv.javaguru.java2.servlet;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FrontPageController extends MVCController{

    private CategoryDAOImpl categoryDAO;
    private ProductDAOImpl productDAO;

    public FrontPageController(CategoryDAOImpl categoryDAO, ProductDAOImpl productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

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
