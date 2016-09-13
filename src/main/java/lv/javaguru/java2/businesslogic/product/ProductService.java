package lv.javaguru.java2.businesslogic.product;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ProductService {

    Map<String, Object> getById(long id, String ip) throws ServiceException;
    List<Product> getAll();
    List<Product> getByCategory(Category category);

}
