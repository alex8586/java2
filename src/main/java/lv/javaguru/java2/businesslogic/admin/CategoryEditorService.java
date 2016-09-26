package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.domain.Category;

import java.util.Map;

public interface CategoryEditorService {

    Map<String, Object> getCategoryList();

    void delete(long categoryId);

    void edit(Category category);
}
