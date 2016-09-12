package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.helpers.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;

    @Autowired
    UserProvider userProvider;

    @Autowired
    CategoryTree categoryTree;

    @Autowired
    private CountVisitService countVisitService;

    public Map<String, Object> getById(long id, String ip) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        Product product = productDAO.getById(id);
        if (product == null)
            throw new RecordIsNotAvailable();
        if (userProvider.authorized())
            countVisitService.countVisit(product);
        else {
            countVisitService.countVisit(product, ip);
        }
        map.put("product", product);
        return map;
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public List<Product> getByCategory(Category category) {
        return productDAO.getByCategoryTree(category);
    }
}
