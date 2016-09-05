package lv.javaguru.java2.businesslogic.adminka;

import lv.javaguru.java2.domain.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maksims on 8/31/2016.
 */
@Component
public class ProductTableFactory {

    private List<Product> list;
    private List<Map<String,String>> rows;
    private Map<String,String> map = new HashMap<>();
    private final String ID_COLUMN_NAME="id";
    private final String NAME_COLUMN_NAME="Product name";
    private final String DESCRIPTION_COLUMN_NAME = "Description";
    private final String REMAINS_COLUMN_NAME = "remains";






    public void setList(List<Product> list) {
        this.list = list;
    }



    public List<Map<String,String>> getTable(){

        List<Map<String,String>> table = new LinkedList<>();

        for (Product item:list
             ) {
            table.add(getRow(item));
        }

        return table;
    }

    private Map<String,String> getRow(Product product){


        Map<String,String> row = new HashMap<>();

        row.put(ID_COLUMN_NAME,String.valueOf(product.getId()));
        row.put(NAME_COLUMN_NAME,product.getName());
        row.put(DESCRIPTION_COLUMN_NAME,product.getDescription());
        row.put(REMAINS_COLUMN_NAME,String.valueOf(product.getQty()));
        row.put("price",String.valueOf(product.getPrice()));

        System.out.println(product.getName());

        return row;
    }

}
