package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.product.ProductService;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.builders.ProductCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCardUtil productCardUtil;

    public Map<String, Object> model(){
        Map<String, Object> model = new HashMap<>();
        List<Product> productList = productService.getAll();


        return model;
    }
}
