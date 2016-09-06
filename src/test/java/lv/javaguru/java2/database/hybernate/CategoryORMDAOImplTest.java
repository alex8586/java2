package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.assertEquals;

public class CategoryORMDAOImplTest extends CrudHybernateDAOTest<Category, CategoryORMDAOImpl> {

    @Autowired
    ProductORMDAOImpl productDAO;

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

    @Test(expected = ConstraintViolationException.class)
    public void creatingWithNullNameFails() {
        Category record = newRecord();
        record.setName(null);
        dao.create(record);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updatingWithNullNameFails() {
        recordFromDAO.setName(null);
        dao.update(recordFromDAO);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void savingWithVeryLongNameFails() {
        String veryLongName = StringUtils.leftPad("name", 512, '*');
        recordFromDAO.setName(veryLongName);
        dao.update(recordFromDAO);
    }

    @Test(expected = ConstraintViolationException.class)
    public void cantDeleteWithProducts() {
        Product product = new Product();
        product.setCategoryId(recordFromDAO.getId());
        productDAO.create(product);
        dao.delete(recordFromDAO);
    }
}