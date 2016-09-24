package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.BaseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


public class SpringPathResolver {

    public static ModelAndView redirectTo(Class controller) {
        return new ModelAndView("redirect:" + MvcUriComponentsBuilder.fromController(controller).build().toString());
    }

    public static ModelAndView redirectTo(Class controller, BaseEntity baseEntity) {
        return redirectTo(controller, baseEntity.getId());
    }

    public static ModelAndView redirectTo(Class controller, long id) {
        return new ModelAndView("redirect:" + MvcUriComponentsBuilder
                .fromController(controller)
                .build()
                .expand(String.valueOf(id)));
    }
}
