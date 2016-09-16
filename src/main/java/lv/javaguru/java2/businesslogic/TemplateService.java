package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.User;

import java.util.Map;

public interface TemplateService {
    Map<String, Object> model(User user);

    Map<String, Object> model();
}
