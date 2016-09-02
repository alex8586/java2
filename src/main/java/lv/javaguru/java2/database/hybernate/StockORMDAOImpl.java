package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.StockDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;

import java.util.Date;
import java.util.List;


public class StockORMDAOImpl implements StockDAO {
    @Override
    public long create(Stock stock) {
        return 0;
    }

    @Override
    public void update(Stock stock) {

    }

    @Override
    public void delete(Stock stock) {

    }

    @Override
    public Stock getById(long id) {
        return null;
    }

    @Override
    public List<Stock> getAll() {
        return null;
    }

    @Override
    public List<Stock> allByProduct(Product product) {
        return null;
    }

    @Override
    public int countFreshByProduct(Product product, Date date) {
        return 0;
    }

    @Override
    public int countExpiredByProduct(Product product, Date date) {
        return 0;
    }
}
