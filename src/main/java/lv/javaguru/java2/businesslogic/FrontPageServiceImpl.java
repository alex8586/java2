package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.ProductCard;
import lv.javaguru.java2.helpers.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FrontPageServiceImpl implements FrontPageService {

    @Autowired
    UserProvider userProvider;
    @Autowired
    ProductService productService;
    @Autowired
    ProductCardServiceImpl productCardService;
    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;
    @Autowired
    private CategoryTree categoryTree;

    public Map<String, Object> serve(Category category) {

        Map<String, Object> frontPageData = new HashMap<String, Object>();
        List<Product> productList = null;
        Product offer;

        if (category == null) {
            productList = productService.getAll();
            offer = specialSaleOffer.getOffer();
        } else {
            productList = productService.getByCategory(category);
            offer = specialSaleOffer.getOffer(category.getId());
        }
        List<ProductCard> productCards = productCardService.forProductList(productList);

        frontPageData.put("user", userProvider.getUser());
        frontPageData.put("categories", categoryTree.asOrderedList());
        frontPageData.put("productCards", productCards);
        frontPageData.put("saleOffer", offer);
        return frontPageData;
    }
}
