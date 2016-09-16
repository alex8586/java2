package lv.javaguru.java2.businesslogic.frontpage;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.checkout.CartService;
import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.ProductCard;
import lv.javaguru.java2.dto.builders.ProductCardUtil;
import lv.javaguru.java2.helpers.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FrontPageServiceImpl implements FrontPageService {

    @Autowired
    ProductService productService;
    @Autowired
    ProductCardUtil productCardUtil;
    @Autowired
    private CategoryTree categoryTree;
    @Autowired
    private CartService cartService;
    @Autowired
    private TemplateService templateService;

    public Map<String, Object> model(Category category) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<Product> productList;
        if (category == null) {
            productList = productService.getAll();
        } else {
            productList = productService.getByCategory(category);
        }
        List<ProductCard> productCards = productCardUtil.build(productList);
        map.put("categories", categoryTree.asOrderedList());
        map.put("productCards", productCards);
        map.putAll(templateService.model());
        map.putAll(cartService.model());
        return map;
    }
}
