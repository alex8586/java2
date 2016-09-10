package lv.javaguru.java2.businesslogic.adminka;

import lv.javaguru.java2.database.hybernate.ProductORMDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maksims on 9/5/2016.
 */
@Component
public class AdminPageDataServiceImpl implements AdminPageDataService {

    @Autowired
    ProductTableFactory productTableFactory;
    @Autowired
    ProductORMDAOImpl productDAO;

    private HttpServletRequest req;
    private Map<String,Object> dataMap = new HashMap<>();

    private List<Product> productList = new LinkedList<>();
    private List<Map<String,String>> productTable;
    private String aspect = "table";




    public AdminPageDataServiceImpl() {


    }


    @Override
    public void init(HttpServletRequest request) {
        if(request.getParameter("productAspect")!= null){
aspect=request.getParameter("productAspect");}

    }

    @Override
    public Map<String, Object> getDataAsMap() {
        productTableFactory.setList(productDAO.getAll());
        dataMap.put(PRODUCT_TABLE_ROWS,productTableFactory.getTable());
        dataMap.put(PRODUCT_PAGE_ASPECT,aspect);


        return dataMap;
    }
}
