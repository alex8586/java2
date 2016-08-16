package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
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
        UserDAOImpl userDAO = new UserDAOImpl();

        FrontPageController frontPageController= new FrontPageController(categoryDAO);
        RegistrationController registrationController = new RegistrationController(userDAO);
        LoginController loginController = new LoginController(userDAO);
        LogoutController logoutController = new LogoutController();
        ProfileController profileController = new ProfileController(userDAO);

        controllers.put("/index", frontPageController);
        controllers.put("/register", registrationController);
        controllers.put("/login", loginController);
        controllers.put("/logout", logoutController);
        controllers.put("/profile", profileController);

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
