package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.domain.BaseEntity;
import org.hibernate.PersistentObjectException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public abstract class CrudHybernateDAOTest<RecordClass extends BaseEntity, DAOClass extends CrudDAO<RecordClass>> {

    protected Random random = new Random();
    protected RecordClass newRecord;
    protected RecordClass recordFromDAO;

    @Autowired
    protected DAOClass dao;
    protected DatabaseCleaner cleaner = new DatabaseCleaner();

    protected abstract RecordClass newRecord();

    protected abstract void fillRecordWithData(RecordClass record);

    protected abstract void makeChangesInRecord(RecordClass record1);

    protected abstract void compareRecords(RecordClass record1, RecordClass record2);

    protected void insertRandomDummyRecords(int count) {
        for (int i = 0; i < count; i++) {
            RecordClass record = newRecord();
            fillRecordWithData(record);
            dao.create(record);
        }
    }

    @Before
    public void before() {
        newRecord = newRecord();
        fillRecordWithData(newRecord);
        dao.create(newRecord);
        recordFromDAO = dao.getById(newRecord.getId());
    }

    @Test(expected = Exception.class)
    public void createWithNullFails() {
        dao.create(null);
    }

    @Test(expected = PersistentObjectException.class)
    public void createWithNonZeroIdFails() {
        RecordClass fresh = newRecord();
        fillRecordWithData(fresh);
        fresh.setId(132);
        dao.create(fresh);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullFails() {
        dao.update(null);
    }

    @Test(expected = ConcurrencyFailureException.class)
    public void updateWithZeroFails() {
        RecordClass fresh = newRecord();
        fillRecordWithData(fresh);
        dao.update(fresh);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullDoNothing() {
        dao.delete(null);
    }

    @Test
    public void findWithWrongIdReturnsNull() {
        RecordClass record = dao.getById(-1);
        assertNull(record);
    }

    @Test
    public void createReturnsId() {
        RecordClass record = newRecord();
        fillRecordWithData(record);
        long id = dao.create(record);
        assertTrue(record.getId() > 0);
        assertTrue(record.getId() == id);
    }

    @Test
    public void testCanFindCreatedRecord() {
        assertTrue(recordFromDAO.getId() > 0);
        compareRecords(newRecord, recordFromDAO);
    }

    @Test
    public void testCouldNotFindDeletedRecord() {
        System.out.println("start");
        long id = recordFromDAO.getId();
        dao.delete(recordFromDAO);

        recordFromDAO = dao.getById(id);
        assertNull(recordFromDAO);
    }

    @Test
    public void testCanSeeUpdatesAfterUpdate() {
        makeChangesInRecord(recordFromDAO);
        dao.update(recordFromDAO);
        RecordClass updatedRecord = dao.getById(recordFromDAO.getId());
        compareRecords(recordFromDAO, updatedRecord);
    }

    @Test
    public void testGetAll() {
        List<RecordClass> records;

        records = dao.getAll();
        assertEquals(1, records.size());

        insertRandomDummyRecords(3);
        records = dao.getAll();
        assertEquals(4, records.size());

        insertRandomDummyRecords(2);
        records = dao.getAll();
        assertEquals(6, records.size());

        dao.delete(records.get(0));
        records = dao.getAll();
        assertEquals(5, records.size());

        dao.delete(records.get(4));
        records = dao.getAll();
        assertEquals(4, records.size());

        dao.delete(records.get(1));
        records = dao.getAll();
        assertEquals(3, records.size());

        insertRandomDummyRecords(5);
        records = dao.getAll();
        assertEquals(8, records.size());
    }


}