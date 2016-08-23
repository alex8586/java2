package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProfileController extends MVCController {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String USER_ALREADY_EXISTS = "User with this email already exists";
    private final String UNEXPECTED_ERROR = "Oops, something went wrong";
    private final String USER_UPDATED = "Information succesfully updated !";

    private UserDAOImpl userDAO;
    private ProductDAOImpl productDAO;

    public ProfileController(UserDAOImpl userDAO, ProductDAOImpl productDAO) {
        this.userDAO = userDAO;
        this.productDAO = productDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return new MVCModel("/index");
        }

        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();

        String imgPath;
        int bannerId;
        List<Product> productList = productDAO.getAll();
        if (productList.size() == 0) {
            imgPath = "miskaweb/img/default.jpg";
        } else {
            Random random = new Random();
            bannerId = random.nextInt(productList.size());
            imgPath = productList.get(bannerId).getImgUrl();
        }

        map.put("imgPath", imgPath);
        map.put("user", user);

        if (request.getSession().getAttribute("profileError") != null) {
            String error = (String) request.getSession().getAttribute("profileError");
            request.getSession().removeAttribute("profileError");

            map.put("profileError", error);
            map.put("user", user);
            return new MVCModel(map, "/profile.jsp");
        }

        return new MVCModel(map, "/profile.jsp");
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {

        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.getSession().setAttribute("profileError", EMPTY_FIELDS);
            return new MVCModel("/profile");
        }

        try {
            User userCheckedByEmail = userDAO.getByEmail(email);
            User fromSession = (User) request.getSession().getAttribute("user");

            if (userCheckedByEmail == null || email.equals(fromSession.getEmail())) {
                User user = (User) request.getSession().getAttribute("user");
                user.setFullName(name);
                user.setEmail(email);
                user.setPassword(password);
                userDAO.update(user);

                request.getSession().removeAttribute("user");
                request.getSession().setAttribute("user", user);

                Map<String, Object> map = new HashMap<>();
                map.put("user", user);
                map.put("profileError", USER_UPDATED);
                return new MVCModel(map, "/profile.jsp");

            } else if (userCheckedByEmail != null) {
                request.getSession().setAttribute("profileError", USER_ALREADY_EXISTS);
                return new MVCModel("/profile");
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("profileError", UNEXPECTED_ERROR);
        return new MVCModel("/profile");
    }
}
