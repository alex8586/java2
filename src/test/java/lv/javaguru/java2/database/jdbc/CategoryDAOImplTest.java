package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.BaseEntityTest;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CategoryDAOImplTest extends BaseEntityTest<Category, CategoryDAOImpl> {

    @Override
    protected void initDAO() {
        testedDAO = new CategoryDAOImpl();
    }

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
        Random random = new Random();
        category.setName("category" + random.nextInt(100000));
    }

    @Override
    protected void makeChangesInRecord(Category category) {
        category.setName("another " + category.getName());
    }


    @Test(expected = DBException.class)
    public void creatingWithNullNameFails() {
        Category record = newRecord();
        testedDAO.create(record);
    }

    @Test(expected = DBException.class)
    public void updatingWithNullNameFails() {
        recordFromDAO.setName(null);
        testedDAO.update(recordFromDAO);
    }
}