package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.RateValidationService;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.ProductCard;
import lv.javaguru.java2.dto.builders.ProductCardUtil;
import lv.javaguru.java2.helpers.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    private static final String CANT_RATE = "allready rated";
    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;
    @Autowired
    UserProvider userProvider;
    @Autowired
    CategoryTree categoryTree;
    @Autowired
    private CountVisitService countVisitService;
    @Autowired
    private ProductCardUtil productCardUtil;

    @Autowired
    private StatisticCountService statisticCountService;

    @Autowired
    private RateValidationService rateValidationService;
    @Autowired
    private TemplateService templateService;


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

        if(!rateValidationService.canRate(userProvider.getUser(), id)){
            map.put("cantRate",CANT_RATE);
        }

        ProductCard productCard = productCardUtil.build(product);
        productCardUtil.build(productCard, product.getRates());
        productCard.setViewCount(statisticCountService.getProductViews(id));
        map.put("productCard", productCard);

        map.put("reviews", product.getReviews());
        map.put("categories", categoryTree.asOrderedList());
        map.putAll(templateService.model(userProvider.getUser()));
        return map;
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public List<Product> getByCategory(Category category) {
        return productDAO.getByCategoryTree(category);
    }

}
