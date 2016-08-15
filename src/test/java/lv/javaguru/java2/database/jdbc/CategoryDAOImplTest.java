package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.IntegrationTest;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class CategoryDAOImplTest extends IntegrationTest {

    private CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

    @Test
    public void findWithWrongIdReturnsNull() {
        Category category = categoryDAOImpl.getById(-1);
        assertNull(category);
    }

    @Test
    public void testCreateReturnId() {
        Category category = new Category();
        category.setName("foo");
        long id = categoryDAOImpl.create(category);
        assertTrue(id > 0 );
    }

    @Test
    public void testCanFindCreatedRecord() {
        Category category = getDummyRecordFromDb();
        long id = category.getId();
        assertFalse(id == 0);
        Category anotherCategory = categoryDAOImpl.getById(id);

        assertNotNull(anotherCategory);
        assertEquals(category.getName(),anotherCategory.getName());
        assertEquals(category.getId(),anotherCategory.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCategoryWithIdShouldFail() {
        Category category = new Category();
        category.setName("Dairy");
        long id1 = categoryDAOImpl.create(category);
        assertTrue(true);
        long id2 = categoryDAOImpl.create(category);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCategoryWithNullShouldFail()  {
        Category category = null;
        categoryDAOImpl.create(category);
    }

    @Test
    public void testCanSeeUpdatesAfterUpdate() {
        Category category = getDummyRecordFromDb();

        long id = category.getId();
        Category anotherCategory = categoryDAOImpl.getById(id);
        anotherCategory.setName("another name");
        categoryDAOImpl.update(anotherCategory);

        Category yetAnotherCategory = categoryDAOImpl.getById(id);
        assertEquals(anotherCategory.getName(),yetAnotherCategory.getName());
        assertEquals(anotherCategory.getId(),yetAnotherCategory.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNewRecordSholdFail() {
        Category category = new Category();
        category.setName("Dairy");
        categoryDAOImpl.update(category);
    }
    @Test(expected = DBException.class)
    public void updateWithoutIdShouldFail() {
        Category category = new Category();
        category.setName("Dairy");
        category.setId(-100500);
        categoryDAOImpl.update(category);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNullRecordSholdFail() {
        Category category = null;
        categoryDAOImpl.update(category);
    }

    @Test
    public void testCouldNotFindDeletedRecord() {
        Category category = getDummyRecordFromDb();
        long id = category.getId();

        Category anotherCategory = categoryDAOImpl.getById(id);
        categoryDAOImpl.delete(anotherCategory);
        assertTrue(anotherCategory.getId() == 0);

        Category yetAnotherCategory = categoryDAOImpl.getById(id);
        assertNull(yetAnotherCategory);
    }
    @Test
    public void testDelitingNullDoNothing() {
        Category category = null;
        categoryDAOImpl.delete(category);
    }


    @Test
    public void testFindAll() {
        insertDummyRecordToDb("name1");
        insertDummyRecordToDb("name2");
        insertDummyRecordToDb("name3");

        List<Category> categories = categoryDAOImpl.getAll();
        assertEquals(3 , categories.size());
        assertEquals("name1" , categories.get(0).getName());
        assertEquals("name2" , categories.get(1).getName());
        assertEquals("name3" , categories.get(2).getName());

        categoryDAOImpl.delete(categories.get(1));
        categories = categoryDAOImpl.getAll();
        assertEquals(2 , categories.size());
        assertEquals("name1" , categories.get(0).getName());
        assertEquals("name3" , categories.get(1).getName());

        insertDummyRecordToDb("name4");
        insertDummyRecordToDb("name5");

        categories = categoryDAOImpl.getAll();
        assertEquals(4 , categories.size());
        assertEquals("name4" , categories.get(2).getName());
        assertEquals("name5" , categories.get(3).getName());
    }

    private void insertDummyRecordToDb(String name) {
        Category category = new Category();
        category.setName(name);
        categoryDAOImpl.create(category);
    }

    private Category getDummyRecordFromDb() {
        Category category = new Category();
        category.setName("name");
        categoryDAOImpl.create(category);
        return category;
    }
}