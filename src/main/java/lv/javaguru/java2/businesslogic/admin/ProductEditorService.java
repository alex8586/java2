package lv.javaguru.java2.businesslogic.admin;

import java.util.Map;

public interface ProductEditorService {

    Map<String, Object> getProductList();

    void delete(long productId);
}
