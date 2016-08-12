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
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);

        String userName = null;
        try {
            User userCheckedByEmail = userDAO.getByEmail(user.getEmail());
            if(userCheckedByEmail!= null){
                String error = "User with the same email already exist";
                return new MVCModel(error, "/error.jsp");
            }
                userDAO.create(user);
            User userFromDao = userDAO.getByEmail(email);
            userName = userFromDao.getFullName();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new MVCModel(userName, "/frontpageSkeleton.jsp");
    }
}
