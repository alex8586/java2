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
public class ProductEditorServiceImpl implements ProductEditorService {

    @Autowired
    @Qualifier("ORM_ProductDAO")
    private ProductDAO productDAO;
    @Autowired
    @Qualifier("ORM_CategoryDAO")
    private CategoryDAO categoryDAO;
    @Autowired
    private TemplateService templateService;

    @Override
    public Map<String, Object> getProductList(){
        Map<String, Object> map = new HashMap<>();
        List<Product> products = productDAO.getAll();
        List<Category> categories = categoryDAO.getAll();
        map.put("categoryList", categories);
        map.put("productList", products);
        map.putAll(templateService.model());
        return map;
    }

    @Override
    public void delete(long productId) {
        Product product = productDAO.getById(productId);
        productDAO.delete(product);

    }

    @Override
    public void update(Product product){
        productDAO.update(product);
    }
}
