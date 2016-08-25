package lv.javaguru.java2.servlet;

import lv.javaguru.java2.config.SpringConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router implements Filter {

    private ApplicationContext springContext;
    private Map<String, MVCController> controllers = new HashMap<String, MVCController>();

    public void init(FilterConfig filterConfig) throws ServletException {

        try {
            springContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        } catch (BeansException e) {

        }

        MVCController registrationController = getBean(RegistrationController.class);
        MVCController loginController = getBean(LoginController.class);
        MVCController logoutController = getBean(LogoutController.class);

        MVCController profileUpdateController = getBean(ProfileUpdateController.class);
        MVCController profileController = getBean(ProfileController.class);
        MVCController profileCartController = getBean(ProfileCartController.class);
        MVCController profileHistoryController = getBean(ProfileHistoryController.class);

        MVCController frontPageController = getBean(FrontPageController.class);
        MVCController categoryChooseController = getBean(CategoryChooseController.class);
        MVCController productController = getBean(ProductController.class);

        controllers.put("/index", frontPageController);
        controllers.put("/index/category", categoryChooseController);

        controllers.put("/register", registrationController);
        controllers.put("/login", loginController);
        controllers.put("/logout", logoutController);
        controllers.put("/profile", profileController);
        controllers.put("/profile/cart", profileCartController);
        controllers.put("/profile/history", profileHistoryController);
        controllers.put("/profile/update", profileUpdateController);
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

    private MVCController getBean(Class clazz) {
        return (MVCController) springContext.getBean(clazz);
    }
}
