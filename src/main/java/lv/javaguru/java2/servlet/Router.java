package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router implements Filter {

    private Map<String, MVCController> controllers = new HashMap<String, MVCController>();

    public void init(FilterConfig filterConfig) throws ServletException {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();

        FrontPageController frontPageController = new FrontPageController(categoryDAO, productDAO);
        RegistrationController registrationController = new RegistrationController(userDAO);
        LoginController loginController = new LoginController(userDAO);
        LogoutController logoutController = new LogoutController();
        ProfileController profileController = new ProfileController(userDAO);
        ProfileCartController profileCartController = new ProfileCartController();
        ProfileHistoryController profileHistoryController = new ProfileHistoryController();
        ProfileUpdateController profileUpdateController = new ProfileUpdateController(userDAO);
        ProductController productController = new ProductController();
        CategoryChooseController categoryChooseController = new CategoryChooseController();

        controllers.put("/index", frontPageController);
        controllers.put("/index/category", categoryChooseController);

        controllers.put("/register", registrationController);
        controllers.put("/login", loginController);
        controllers.put("/logout", logoutController);
        controllers.put("/profile", profileController);
        controllers.put("/profile_cart", profileCartController);
        controllers.put("/profile_history", profileHistoryController);
        controllers.put("/profile_update", profileUpdateController);
        controllers.put("/product", productController);

        ReverseRouter reverseRouter = new ReverseRouter(controllers, "/error.jsp");
        filterConfig.getServletContext().setAttribute("reverseRouter", reverseRouter);
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String contextURI = req.getServletPath();
        MVCController controller = controllers.get(contextURI);
        if (controller != null) {
            MVCModel model;
            if(((HttpServletRequest) request).getMethod().equals("POST"))
                model = controller.doPost(req);
            else
                model = controller.doGet(req);
            req.setAttribute("model", model.getData());

            if(model.isRedirect()) {
                System.out.println("redirecting to ... " + model.getViewName());
                resp.sendRedirect(model.getViewName());
            }
            else {
                System.out.println("forwarding to ... " + model.getViewName());
                RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(model.getViewName());
                requestDispatcher.forward(req, resp);
            }
        }
        else {
            System.out.println("unable to find controller for URI " + contextURI);
            filterChain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}
