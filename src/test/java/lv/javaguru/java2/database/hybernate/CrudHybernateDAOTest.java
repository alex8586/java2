package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.domain.BaseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;


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

}