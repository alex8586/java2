package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ContactController extends MVCController {

    private CategoryDAOImpl categoryDAO;

    public ContactController(CategoryDAOImpl categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categories" , categoryDAO.getAll());

        if (request.getSession().getAttribute("user") == null) {
            return new MVCModel(map, "/contact.jsp");
        }

        User user = (User) request.getSession().getAttribute("user");
        map.put("user", user);

        return new MVCModel(map, "/contact.jsp");
    }

}
