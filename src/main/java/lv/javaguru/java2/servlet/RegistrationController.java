package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;

public class RegistrationController extends MVCController {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String UNEXPECTED_ERROR = "Oops, something went wrong";
    private final String USER_ALREADY_EXISTS = "User already exists";

    private UserDAOImpl userDAO;
    public RegistrationController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected MVCModel executeGet(HttpServletRequest request) {
        String error = (String) request.getSession().getAttribute("registrationError");
        return new MVCModel(error,"/registration.jsp");
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {

        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            request.getSession().setAttribute("registrationError" , EMPTY_FIELDS);
            return new MVCModel("/register");
        }

        try {
            User userCheckedByEmail = userDAO.getByEmail(email);
            if(userCheckedByEmail!= null){
                request.getSession().setAttribute("registrationError" , USER_ALREADY_EXISTS);
                return new MVCModel("/register");
            }
            User user = new User();
            user.setFullName(name);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.create(user);
            return new MVCModel("/login");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("registrationError" , UNEXPECTED_ERROR);
        return new MVCModel("/register");
    }
}
