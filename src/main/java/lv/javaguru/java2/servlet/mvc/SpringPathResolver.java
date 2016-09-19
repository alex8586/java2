package lv.javaguru.java2.servlet.mvc;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;


public class SpringPathResolver {

    public static RedirectView redirectTo(Class controller) {
        return new RedirectView(MvcUriComponentsBuilder.fromController(controller).build().toString());
    }
}
