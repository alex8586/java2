package lv.javaguru.java2.businesslogic.adminka.view_blocks;

import lv.javaguru.java2.businesslogic.adminka.ViewBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Maksims on 9/12/2016.
 */
@Component("name1")
public class ProductViewBlock  {

    private String huy = "product view block";
    private String  jspFile = "product_table.jsp";

    public String getHuy() {
        return huy;
    }

    public void setHuy(String huy) {
        this.huy = huy;
    }

    public String getJspFile() {
        return jspFile;
    }

    public void setJspFile(String jspFile) {
        this.jspFile = jspFile;
    }
}
