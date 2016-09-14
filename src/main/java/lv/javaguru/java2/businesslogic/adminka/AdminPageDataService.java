package lv.javaguru.java2.businesslogic.adminka;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Maksims on 9/5/2016.
 *
 *
 */
public interface AdminPageDataService {

    public static final String PRODUCT_TABLE_ROWS = "tabrows";
    public static final String PRODUCT_VIEW_TABLE_JSP = "product_table.jsp";
    public static final String PRODUCT_VIEW_FORM_JSP = "product_edit.jsp";

    public static final String PRODUCT_VIEW_NAME = "productView";

    void init(HttpServletRequest request);
   Map<String,Object> getDataAsMap();
}
