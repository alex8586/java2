package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.servlet.*;
import lv.javaguru.java2.servlet.frontpage.CategoryChooseController;
import lv.javaguru.java2.servlet.frontpage.CategoryResetController;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.profilepages.*;
import org.springframework.beans.BeansException;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Router implements Filter {

    PathResolver pathResolver;
    private AnnotationConfigWebApplicationContext springContext;

    public void init(FilterConfig filterConfig) throws ServletException {

        try {
            springContext = new AnnotationConfigWebApplicationContext();
            springContext.register(SpringConfig.class);
            springContext.refresh();
        } catch (BeansException e) {
        }
        pathResolver = springContext.getBean(PathResolver.class);

        addController("/checkout", CheckoutController.class);
        addController("/cart", CartController.class);
        addController("/index", FrontPageController.class);
        addController("/index/category", CategoryChooseController.class);
        addController("/index/all", CategoryResetController.class);
        addController("/register", RegistrationController.class);
        addController("/login", LoginController.class);
        addController("/logout", LogoutController.class);
        addController("/profile", ProfileController.class);
        addController("/profile/cart", ProfileCartController.class);
        addController("/profile/history", ProfileHistoryController.class);
        addController("/profile/update", ProfileUpdateController.class);
        addController("/product", ProductController.class);
        addController("/contacts", ContactController.class);
        addController("/profile/shippingProfiles", ShippingProfileController.class);
        addController("/profile/shippingProfiles/delete", ShippingProfileDeleteController.class);

        pathResolver.setAlias(Product.class, ProductController.class);
        pathResolver.setAlias(Category.class, CategoryChooseController.class);
        filterConfig.getServletContext().setAttribute("reverseRouter", pathResolver);

    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String contextURI = req.getServletPath();
        System.out.println(contextURI);
        MVCController controller = pathResolver.getPathFor(contextURI);
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

    private void addController(String route, Class clazz) {
        pathResolver.registerPath(route, (MVCController) springContext.getBean(clazz));
    }
}
