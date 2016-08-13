package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;

public class RegistrationController implements MVCController{

    private UserDAOImpl userDAO;

    public RegistrationController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public MVCModel execute(HttpServletRequest request) {
        System.out.println("in RegistrationController (press button registration)");
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            String errorEmptyFields = "All fields must be filled";
            return new MVCModel(errorEmptyFields, "/registration.jsp");
        }

        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);

        String userName = null;
        try {
            User userCheckedByEmail = userDAO.getByEmail(user.getEmail());
            if(userCheckedByEmail!= null){
                String error = "User with the same email already exist";
                return new MVCModel(error, "/registration.jsp");
            }
                userDAO.create(user);
            User userFromDao = userDAO.getByEmail(email);
            userName = userFromDao.getFullName();

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return new MVCModel(userName, "/index.jsp");
    }
}
