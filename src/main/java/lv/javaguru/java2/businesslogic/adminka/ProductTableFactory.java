package lv.javaguru.java2.businesslogic.adminka;

import lv.javaguru.java2.database.hybernate.CategoryORMDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import org.apache.commons.beanutils.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maksims on 8/31/2016.
 */
@Component
public class ProductTableFactory {
    @Autowired
    CategoryORMDAOImpl categoryORMDAO;
    private List<Product> list;
    private List<Map<String,String>> rows;
    private Map<String,String> map = new HashMap<>();

    public void setList(List<Product> list) {
        this.list = list;
    }



    public List<MyTableRow> getTable() {
        List<MyTableRow> table = new LinkedList<>();
        for (Product item:list) { table.add(getRow(item));}
        return table;
    }

    private MyTableRow getRow(Product product)  {

        Map<String,Object> row = new HashMap<>();

        row.put("id",String.valueOf(product.getId()));
        row.put("name",product.getName());
        row.put("description",product.getDescription());
        row.put("remains",categoryORMDAO.getById(product.getCategoryId()).getName());
        row.put("price",String.valueOf(product.getPrice()));
        row.put("stock",product.getStockList());


MyTableRow tableRow = new MyTableRow();

        try {
            BeanUtils.populate(tableRow,row);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return tableRow;
    }

    public class MyTableRow {
        private String id;

        private String name;
        private String description;
        private String remains;
        private String price;

        public List<Stock> getStock() {
            return stock;
        }

        public void setStock(List<Stock> stock) {
            this.stock = stock;
        }

        private List<Stock> stock;

        public MyTableRow() {
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getRemains() {
            return remains;
        }

        public String getPrice() {
            return price;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setRemains(String remains) {
            this.remains = remains;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
