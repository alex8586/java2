package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.FrontPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class FrontPageController extends MVCController{

    @Autowired
    FrontPageService frontPageService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> frontPageData = frontPageService.serve();
        return new MVCModel(frontPageData, "/frontpage.jsp");
    }
}
