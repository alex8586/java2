package lv.javaguru.java2.database;

import lv.javaguru.java2.crossdomain.StockProduct;

import java.util.List;

public interface StockProductDAO {

    List<StockProduct> getAll();
}
