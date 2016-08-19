package lv.javaguru.java2;

import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.BaseEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public abstract class BaseEntityTest<RecordClass extends BaseEntity, DAOClass extends DAOImpl<RecordClass>> {

    protected RecordClass newRecord;
    protected RecordClass recordFromDAO;
    protected DAOClass testedDAO;
    private DatabaseCleaner cleaner = new DatabaseCleaner();

    public BaseEntityTest() {
        initDAO();
    }

    protected abstract void initDAO();
    protected abstract RecordClass newRecord();

    protected abstract void fillRecordWithData(RecordClass record);

    protected abstract void makeChangesInRecord(RecordClass record1);

    protected abstract void compareRecords(RecordClass record1, RecordClass record2);

    protected void insertRandomDummyRecords(int count) {
        for (int i = 0; i < count; i++) {
            RecordClass record = newRecord();
            fillRecordWithData(record);
            testedDAO.create(record);
        }
    }

    @Before
    public void setup() {
        cleaner.cleanDatabase();
        newRecord = newRecord();
        fillRecordWithData(newRecord);
        testedDAO.create(newRecord);
        recordFromDAO = testedDAO.getById(newRecord.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullFails() {
        testedDAO.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNonZeroIdFails() {
        RecordClass fresh = newRecord();
        fresh.setId(123);
        testedDAO.create(fresh);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullFails() {
        testedDAO.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithZeroFails() {
        RecordClass fresh = newRecord();
        testedDAO.update(fresh);
    }

    @Test
    public void deleteNullDoNothing() {
        testedDAO.delete(null);
    }

    @Test
    public void findWithWrongIdReturnsNull() {
        RecordClass record = testedDAO.getById(-1);
        assertNull(record);
    }

    @Test
    public void createReturnsId() {
        RecordClass record = newRecord();
        fillRecordWithData(record);
        testedDAO.create(record);
        assertTrue(record.getId() > 0);
    }

    @Test
    public void testCanFindCreatedRecord() {
        assertTrue(recordFromDAO.getId() > 0);
        compareRecords(newRecord, recordFromDAO);
    }

    @Test
    public void testCouldNotFindDeletedRecord() {
        long id = recordFromDAO.getId();
        testedDAO.delete(recordFromDAO);
        assertEquals(0, recordFromDAO.getId());
        recordFromDAO = testedDAO.getById(id);
        assertNull(recordFromDAO);
    }

    @Test
    public void testCanSeeUpdatesAfterUpdate() {
        makeChangesInRecord(recordFromDAO);
        testedDAO.update(recordFromDAO);
        RecordClass updatedRecord = testedDAO.getById(recordFromDAO.getId());
        compareRecords(recordFromDAO, updatedRecord);
    }

    @Test
    public void testGetAll() {
        List<RecordClass> records;

        records = testedDAO.getAll();
        assertEquals(1, records.size());

        insertRandomDummyRecords(3);
        records = testedDAO.getAll();
        assertEquals(4, records.size());

        insertRandomDummyRecords(2);
        records = testedDAO.getAll();
        assertEquals(6, records.size());

        testedDAO.delete(records.get(0));
        records = testedDAO.getAll();
        assertEquals(5, records.size());

        testedDAO.delete(records.get(4));
        records = testedDAO.getAll();
        assertEquals(4, records.size());

        testedDAO.delete(records.get(1));
        records = testedDAO.getAll();
        assertEquals(3, records.size());

        insertRandomDummyRecords(5);
        records = testedDAO.getAll();
        assertEquals(8, records.size());
    }
}
