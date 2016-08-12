package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            User user = userDAO.getByEmail(email);
            if(user == null || !user.getPassword().equals(password)){
                response.sendRedirect("error.jsp");
            }else {
                request.setAttribute("name", user.getFullName());
                RequestDispatcher dispatcher = request.getRequestDispatcher("frontpageSkeleton.jsp");
                dispatcher.forward(request, response);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
