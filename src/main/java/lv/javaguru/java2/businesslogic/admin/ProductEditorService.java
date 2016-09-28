package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.domain.Product;

import java.util.Map;

public interface ProductEditorService {

    Map<String, Object> getProductList();

    void delete(long productId);

    void edit(Product product);
}
