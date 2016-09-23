package lv.javaguru.java2.servlet.adminpages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Admin {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request){
        return new ModelAndView("/admin");
    }
}
