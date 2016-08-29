package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CategoryDAOImplTest extends CrudJdbcDAOTest<Category, CategoryDAOImpl> {

    @Override
    protected Category newRecord() {
        return new Category();
    }

    @Override
    protected void compareRecords(Category category1, Category category2) {
        assertEquals(category1.getId(), category2.getId());
        assertEquals(category1.getName(), category2.getName());
    }

    @Override
    protected void fillRecordWithData(Category category) {
        category.setName("category" + random.nextInt(100000));
    }

    @Override
    protected void makeChangesInRecord(Category category) {
        category.setName("another " + category.getName());
    }

    @Before
    public void before() {
        cleaner.cleanDatabase();
        super.before();
    }

    @Test(expected = DBException.class)
    public void creatingWithNullNameFails() {
        Category record = newRecord();
        record.setName(null);
        dao.create(record);
    }

    @Test(expected = DBException.class)
    public void updatingWithNullNameFails() {
        recordFromDAO.setName(null);
        dao.update(recordFromDAO);
    }

    @Test(expected = DBException.class)
    public void savingWithVeryLongNameFails() {
        String veryLongName = StringUtils.leftPad("name", 512, '*');
        recordFromDAO.setName(veryLongName);
        dao.update(recordFromDAO);
    }

    @Test(expected = DBException.class)
    public void cantDeleteWithProducts() {
        Product product = new Product();
        product.setCategoryId(recordFromDAO.getId());
        ProductDAOImpl productDAO = new ProductDAOImpl();
        productDAO.create(product);
        dao.delete(recordFromDAO);
    }
}