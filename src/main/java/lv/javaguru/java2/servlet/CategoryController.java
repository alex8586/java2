package lv.javaguru.java2.servlet;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CategoryController implements MVCController{

    private CategoryDAOImpl categoryDAO;
    public CategoryController(CategoryDAOImpl categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public MVCModel execute(HttpServletRequest request) {
        System.out.println("in category controller");
        Map<String,Object> frontPageData = new HashMap<String,Object>();
        try {
            frontPageData.put("categories" , categoryDAO.getAll());
            frontPageData.put("products" , null);
        }
        catch (DBException dbe){
        }
        return new MVCModel(frontPageData, "/index.jsp");
    }
}
