package lv.javaguru.java2.businesslogic.adminka;

import lv.javaguru.java2.domain.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksims on 8/31/2016.
 */
public class ProductTableFactory {


    private List<Product> list;

public ProductTableFactory(List<Product> productList){

    this.list = productList;
}

    public List getRows(){
List<String> rows = new LinkedList<>();
        for (Product item:list
             ) {
           String tableRow = "<td>"+
                   item.getId()+"</td>"+
                   item.getName()+"</td>"+
                   item.getDescription()+"</td>";

            rows.add(tableRow);


        }

        return rows;
    }
}
