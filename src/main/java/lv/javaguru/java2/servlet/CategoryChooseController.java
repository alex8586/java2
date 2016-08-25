package lv.javaguru.java2.servlet;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
class CategoryChooseController extends MVCController {
    @Override
    protected MVCModel executeGet(HttpServletRequest request) {
        //System.out.println("params " + request.getParameterMap());
        //System.out.println(request.getParameter("id"));
        //return super.executeGet(request);
        return new MVCModel("/index");
    }
}
