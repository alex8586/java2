package lv.javaguru.java2.servlet;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FrontPageController implements MVCController{

    private CategoryDAOImpl categoryDAO;
    public FrontPageController(CategoryDAOImpl categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public MVCModel execute(HttpServletRequest request) {
        Map modelData = new HashMap();
        try {
            Map<String,Object> frontPageData = new HashMap<String,Object>();
            frontPageData.put("categories" , categoryDAO.getAll());
            frontPageData.put("products" , null);
        }
        catch (DBException dbe){
        }
        return new MVCModel(modelData, "/frontPage.jsp");
    }
}
