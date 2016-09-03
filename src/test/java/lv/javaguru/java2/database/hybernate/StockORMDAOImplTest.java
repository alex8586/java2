package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class StockORMDAOImplTest extends CrudHybernateDAOTest<Stock, StockORMDAOImpl> {

    @Autowired
    private CategoryORMDAOImpl categoryDAO;
    @Autowired
    private ProductORMDAOImpl productDAO;

    private Category category = new Category();
    private Product product = new Product();
    private Product otherProduct = new Product();

    private Date today = new Date();
    private Date dayAfterTomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 48));

    @Before
    @Override
    public void before() {
        cleaner.cleanDatabase();
        category.setName("cat");
        categoryDAO.create(category);
        product.setName("prod");
        product.setImgUrl("img");
        product.setDescription("desc");
        product.setPrice(123);
        product.setCategoryId(category.getId());
        productDAO.create(product);
        otherProduct.setName("prod2");
        otherProduct.setImgUrl("img2");
        otherProduct.setDescription("desc2");
        otherProduct.setPrice(1234);
        otherProduct.setCategoryId(category.getId());
        productDAO.create(otherProduct);
        super.before();
    }

    @Override
    protected Stock newRecord() {
        return new Stock();
    }

    @Override
    protected void fillRecordWithData(Stock record) {
        record.setProductId(product.getId());
        record.setExpireDate(dayAfterTomorrow);
        record.setQuantity(42);
    }

    @Override
    protected void makeChangesInRecord(Stock record) {
        record.setProductId(otherProduct.getId());
        record.setExpireDate(new Date(dayAfterTomorrow.getTime() + (1000 * 60 * 60 * 48)));
        record.setQuantity(43);
    }

    @Override
    protected void compareRecords(Stock record1, Stock record2) {
        assertEquals(record1.getId(), record2.getId());
        assertEquals(record1.getProductId(), record2.getProductId());
        assertEquals(record1.getQuantity(), record2.getQuantity());

        assertEquals(DateUtils.truncate(record1.getExpireDate(), Calendar.DATE),
                DateUtils.truncate(record2.getExpireDate(), Calendar.DATE));
    }

    @Test
    public void testCountOnUnexistingProductReturnsZero() {
        Product bad = new Product();
        product.setId(-1);
        assertEquals(0, dao.countExpiredByProduct(product, today));
        assertEquals(0, dao.countFreshByProduct(product, today));
    }

    @Test(expected = NullPointerException.class)
    public void testCountsOnNullDateFails() {
        dao.countExpiredByProduct(product, null);
    }

    @Test(expected = NullPointerException.class)
    public void testCountsOnNullProductFails() {
        dao.countExpiredByProduct(null, today);
    }


    @Test
    public void testCountFreshProducts() {
        assertEquals(42, recordFromDAO.getQuantity());
        Stock stock = newRecord();
        fillRecordWithData(stock);
        dao.create(stock);
        assertEquals(stock.getQuantity() * 2, dao.countFreshByProduct(product, today));
    }

    @Test
    public void testCountExpiredProducts() {
        assertEquals(0, dao.countExpiredByProduct(product, today));

        Stock stock = newRecord();
        fillRecordWithData(stock);
        stock.setExpireDate(new Date(dayAfterTomorrow.getTime() - (1000 * 60 * 60 * 196)));
        System.out.println(stock.getExpireDate());
        dao.create(stock);
        assertEquals(42, dao.countExpiredByProduct(product, today));
        recordFromDAO.setExpireDate(stock.getExpireDate());
        recordFromDAO.setQuantity(9);
        dao.update(recordFromDAO);
        assertEquals(51, dao.countExpiredByProduct(product, today));
        assertEquals(0, dao.countFreshByProduct(product, today));
    }

}