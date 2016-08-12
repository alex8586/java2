package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;

public class LoginController implements MVCController{

    private UserDAOImpl userDAO;

    public LoginController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public MVCModel execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("from loginpage");
        String name = null;
        try {
            User user = userDAO.getByEmail(email);
            if(user == null || !user.getPassword().equals(password)){
                String error = "User with the same email not exist";
                return new MVCModel(error, "/login");
            }
                name = user.getFullName();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new MVCModel("","/frontpageSkeleton.jsp");

    }
}
