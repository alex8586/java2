package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.dto.ProductCard;
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
    @Autowired
    private ProductCardService productCardService;
    @Autowired
    private ReviewDAO reviewDAO;
    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private SpecialSaleOffer specialSaleOffer;
    @Autowired
    private StatisticCountService statisticCountService;

    private boolean userCanCommentOnProduct(/*params*/) {
        return userProvider.authorized();
    }

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
        //add @Transactional over Service

        long views = statisticCountService.getProductViews(id);
        map.put("views", views);
        List<Review> reviews = reviewDAO.getByProduct(product);
        map.put("reviews", reviews);
        List<Category> categoryList = categoryDAO.getAll();
        map.put("categories", categoryList);
        Product offer = specialSaleOffer.getOffer();
        map.put("saleOffer", offer);
        ProductCard productCard = productCardService.forProduct(product);
        map.put("productCard", productCard);

        return map;
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public List<Product> getByCategory(Category category) {
        return productDAO.getByCategoryTree(category);
    }
}
