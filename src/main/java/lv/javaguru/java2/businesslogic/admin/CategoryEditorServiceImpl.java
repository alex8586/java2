package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
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
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

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
        List<Product> productList = productDAO.getAllByCategory(category);
        for(Product product : productList){
            product.setCategoryId(1);
            productDAO.update(product);
        }
        categoryDAO.delete(category);
    }

    @Override
    public void edit(Category category){
        categoryDAO.update(category);
    }
}
