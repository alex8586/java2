package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            User userFromDAO = userDAO.getByEmail(user.getEmail());
            if(userFromDAO != null){
                response.sendRedirect("error.jsp");
            }else {

                userDAO.create(user);
                response.sendRedirect("frontpageSkeleton.jsp");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }


    }
}
