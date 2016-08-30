package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Category;

import java.util.Map;

public interface FrontPageService {

    Map<String, Object> serve(Category category);

}
