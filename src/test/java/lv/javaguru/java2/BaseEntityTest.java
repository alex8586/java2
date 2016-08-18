package lv.javaguru.java2;

import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.BaseEntity;
import org.junit.Before;
import org.junit.Test;

public abstract class BaseEntityTest<RecordClass extends BaseEntity, DAOClass extends DAOImpl> {

    protected RecordClass newRecord;
    protected RecordClass recordFromDAO;
    protected DAOClass testedDAO;
    private DatabaseCleaner cleaner = new DatabaseCleaner();

    public BaseEntityTest() {
        initDAO();
    }

    protected abstract void initDAO();

    protected abstract RecordClass newRecord();

    protected abstract void initRecordWithData(RecordClass record);
    //abstract void makeChangesInRecord(RecordClass record1) throws Exception;
    //abstract void compareRecords(RecordClass record1 , RecordClass record2);

    @Before
    public void setup() {
        cleaner.cleanDatabase();
        newRecord = newRecord();
        initRecordWithData(newRecord);
        testedDAO.create(newRecord);
        recordFromDAO = (RecordClass) testedDAO.getById(newRecord.getId());
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

}
