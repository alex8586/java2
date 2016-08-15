package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginController extends MVCController{

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String WRONG_EMAIL = "user with such email not found";
    private final String WRONG_PASSWORD = "wrong password";
    private UserDAOImpl userDAO;

    public LoginController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        String error = null;
        if(request.getSession().getAttribute("loginError") != null) {
             error = (String) request.getSession().getAttribute("loginError");
             request.removeAttribute("loginError");
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loginError" , error);
        return new MVCModel(map,"/login.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email.isEmpty() || password.isEmpty()){
            request.getSession().setAttribute("loginError" , EMPTY_FIELDS);
            return new MVCModel("/login");
        }

        try {
            User user = userDAO.getByEmail(email);
            String error = null;
            if(user == null){
                error = WRONG_EMAIL;
            }
            else if(!user.getPassword().equals(password)) {
                error = WRONG_PASSWORD;
            }
            if(error != null){
                request.getSession().setAttribute("loginError" , error);
                return new MVCModel("/login");
            }
            else{
                request.getSession().setAttribute("user" , user);
                return new MVCModel("/index");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new MVCModel("/login");

    }
}
