package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.domain.Category;
import org.junit.Before;

import static org.junit.Assert.assertEquals;


public class CategoryORMDAOImplTest extends CrudHybernateDAOTest<Category, CategoryORMDAOImpl> {

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

}