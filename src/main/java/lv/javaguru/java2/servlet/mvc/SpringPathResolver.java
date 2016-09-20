package lv.javaguru.java2.servlet.mvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


public class SpringPathResolver {

    public static ModelAndView redirectTo(Class controller) {
        return new ModelAndView("redirect:" + MvcUriComponentsBuilder.fromController(controller).build().toString());
    }
}
