package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;

import java.util.Date;
import java.util.List;

public interface StockDAO extends CrudDAO<Stock> {
    List<Stock> allByProduct(Product product);
    int countFreshByProduct(Product product, Date date);
    int countExpiredByProduct(Product product, Date date);
}
