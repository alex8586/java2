package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.database.ProductDAO;
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

    @Override
    public Map<String, Object> getProductList(){
        Map<String, Object> map = new HashMap<>();
        List<Product> list = productDAO.getAll();
        map.put("productList", list);
        return map;
    }

    @Override
    public void delete(long productId) {
        Product product = productDAO.getById(productId);
        System.out.println("--------------- product " + product.toString());
        productDAO.delete(product);
    }

}
