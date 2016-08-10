package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router implements Filter {

    private Map<String, MVCController> controllers;


    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("hiiiii");
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        //YourStuff yourStuff = new YourStuff(moreStuff,evenMoreStuff);

        FrontPageController frontPageController = new FrontPageController(categoryDAO);
        //YourController yourController = new YourController(yourstuff,categoryDAO,whatever)
        controllers = new HashMap<String, MVCController>();
        controllers.put("/",frontPageController);
        //controllers.put("/youraddress",yourController);
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String contextURI = req.getServletPath();
        MVCController controller = controllers.get(contextURI);
        if (controller != null) {
            MVCModel model = controller.execute(req);
            req.setAttribute("model", model.getData());
            ServletContext context = req.getServletContext();
            RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getViewName());
            requestDispatcher.forward(req, resp);
        }
        else filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}
