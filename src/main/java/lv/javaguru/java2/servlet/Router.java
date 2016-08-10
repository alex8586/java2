package lv.javaguru.java2.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router implements Filter {

    private Map<String, MVCController> controllers;


    public void init(FilterConfig filterConfig) throws ServletException {
        controllers = new HashMap<String, MVCController>();

        //controllers.put("/hello", );
        //HelloWorldController helloController = new HelloWorldController();
        //controllers.put("/", helloController);
        //controllers.put("/hello", helloController);
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
            RequestDispatcher requestDispacher =
                    context.getRequestDispatcher(model.getViewName());
            requestDispacher.forward(req, resp);
        }
        else filterChain.doFilter(request,response);
    }

    public void destroy() {

    }

}
