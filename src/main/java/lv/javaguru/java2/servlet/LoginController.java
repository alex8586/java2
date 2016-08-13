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
        System.out.println("in LoginController (press button login)");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email.isEmpty() || password.isEmpty()){
            String errorEmptyFields = "All fields must be filled";
            return new MVCModel(errorEmptyFields, "/login.jsp");
        }

        String name = null;
        try {
            User user = userDAO.getByEmail(email);
            if(user == null){
                String errorByMail = "User with this email not exist";
                return new MVCModel(errorByMail, "/login.jsp");
            }else if(!user.getPassword().equals(password)){
                String errorByPassword = "Wrong password";
                return new MVCModel(errorByPassword, "/login.jsp");
            }
                name = user.getFullName();
        } catch (DBException e) {
            e.printStackTrace();
        }

        return new MVCModel(name,"/index.jsp");

    }
}
