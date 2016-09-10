package lv.javaguru.java2.businesslogic.adminka;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Maksims on 9/5/2016.
 *
 *
 */
public interface AdminPageDataService {

    public static final String PRODUCT_TABLE_ROWS = "tabrows";
    public static final String PRODUCT_PAGE_ASPECT = "productAspect";

   void init(HttpServletRequest request);
   Map<String,Object> getDataAsMap();
}
