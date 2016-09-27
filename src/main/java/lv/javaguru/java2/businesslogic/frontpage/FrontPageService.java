package lv.javaguru.java2.businesslogic.frontpage;

import lv.javaguru.java2.domain.Category;

import java.util.Map;

public interface FrontPageService {

    Map<String, Object> model(Category category, String sortingStrategy);

}
