package lv.javaguru.java2.database;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.CategoryDAO;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class CategoryDAOTest {

    private CategoryDAO categoryDAO = new CategoryDAO();
    private DatabaseCleaner cleaner = new DatabaseCleaner();

    @Before
    public void before() throws DBException{
        cleaner.cleanDatabase();
    }

    @Test
    public void findWithWrongIdReturnsNull() throws DBException {
        Category category = categoryDAO.getById(-1);
        assertNull(category);
    }

    @Test
    public void testCanFindCreatedRecord() throws DBException{
        Category category = getDummyRecordFromDb();
        long id = category.getId();
        assertFalse(id == 0);
        Category anotherCategory = categoryDAO.getById(id);

        assertNotNull(anotherCategory);
        assertEquals(category.getName(),anotherCategory.getName());
        assertEquals(category.getId(),anotherCategory.getId());
    }

    @Test
    public void testCanSeeUpdatesAfterUpdate() throws DBException{
        Category category = getDummyRecordFromDb();

        long id = category.getId();
        Category anotherCategory = categoryDAO.getById(id);
        anotherCategory.setName("another name");
        categoryDAO.update(anotherCategory);

        Category yetAnotherCategory = categoryDAO.getById(id);
        assertEquals(anotherCategory.getName(),yetAnotherCategory.getName());
        assertEquals(anotherCategory.getId(),yetAnotherCategory.getId());
    }

    @Test
    public void testCantFindDeletedRecord() throws DBException{
        Category category = getDummyRecordFromDb();
        long id = category.getId();

        Category anotherCategory = categoryDAO.getById(id);
        categoryDAO.delete(anotherCategory);
        assertTrue(anotherCategory.getId() == 0);

        Category yetAnotherCategory = categoryDAO.getById(id);
        assertNull(yetAnotherCategory);
    }

    @Test
    public void testFindAll() throws DBException{
        insertDummyRecordToDb("name1");
        insertDummyRecordToDb("name2");
        insertDummyRecordToDb("name3");

        List<Category> categories = categoryDAO.getAll();
        assertEquals(3 , categories.size());
        assertEquals("name1" , categories.get(0).getName());
        assertEquals("name2" , categories.get(1).getName());
        assertEquals("name3" , categories.get(2).getName());

        categoryDAO.delete(categories.get(1));
        categories = categoryDAO.getAll();
        assertEquals(2 , categories.size());
        assertEquals("name1" , categories.get(0).getName());
        assertEquals("name3" , categories.get(1).getName());

        insertDummyRecordToDb("name4");
        insertDummyRecordToDb("name5");

        categories = categoryDAO.getAll();
        assertEquals(4 , categories.size());
        assertEquals("name4" , categories.get(2).getName());
        assertEquals("name5" , categories.get(3).getName());
    }

    private void insertDummyRecordToDb(String name) throws DBException{
        Category category = new Category();
        category.setName(name);
        categoryDAO.create(category);
    }

    private Category getDummyRecordFromDb() throws DBException{
        Category category = new Category();
        category.setName("name");
        categoryDAO.create(category);
        return category;
    }

}