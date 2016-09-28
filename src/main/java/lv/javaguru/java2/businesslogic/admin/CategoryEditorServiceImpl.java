package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryEditorServiceImpl implements CategoryEditorService{

    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;
    @Autowired
    private TemplateService templateService;

    @Override
    public Map<String, Object> getCategoryList() {
        Map<String, Object> map = new HashMap<>();
        List<Category> categories = categoryDAO.getAll();
        map.put("categories", categories);
        map.putAll(templateService.model());
        return map;
    }

    @Override
    public void delete(long categoryId){
        Category category = categoryDAO.getById(categoryId);
        categoryDAO.delete(category);
    }

    @Override
    public void edit(Category category){
        categoryDAO.update(category);
    }
}
