package lv.javaguru.java2.servlet.frontpage;

import lv.javaguru.java2.businesslogic.frontpage.FrontPageService;
import lv.javaguru.java2.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class FrontPageController {

    @Autowired
    FrontPageService frontPageService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/frontpage");
        Map<String, Object> frontPageData = frontPageService.model((Category) request.getSession().getAttribute("currentCategory"));
        for(Map.Entry<String, Object> entry : frontPageData.entrySet()){
            model.addObject(entry.getKey(), entry.getValue());
        }
        return model;
    }
}
