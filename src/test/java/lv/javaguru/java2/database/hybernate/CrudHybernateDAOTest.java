package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.database.CrudDAOTest;
import lv.javaguru.java2.domain.BaseEntity;
import org.hibernate.PersistentObjectException;
import org.junit.Test;
import org.springframework.dao.ConcurrencyFailureException;

import static junit.framework.TestCase.assertNull;

public abstract class CrudHybernateDAOTest<RecordClass extends BaseEntity, DAOClass extends CrudDAO<RecordClass>>
        extends CrudDAOTest<RecordClass, DAOClass> {


    @Test(expected = PersistentObjectException.class)
    public void createWithNonZeroIdFails() {
        RecordClass fresh = newRecord();
        fillRecordWithData(fresh);
        fresh.setId(132);
        dao.create(fresh);
    }

    @Test(expected = ConcurrencyFailureException.class)
    public void updateWithZeroFails() {
        RecordClass fresh = newRecord();
        fillRecordWithData(fresh);
        dao.update(fresh);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullFails() {
        dao.delete(null);
    }

    @Test
    public void testCouldNotFindDeletedRecord() {
        long id = recordFromDAO.getId();
        dao.delete(recordFromDAO);
        //assertEquals(0, recordFromDAO.getId());
        recordFromDAO = dao.getById(id);
        assertNull(recordFromDAO);
    }

    @Test(expected = ConcurrencyFailureException.class)
    public void deleteWithWrongIdFails() {
        RecordClass fresh = newRecord();
        fillRecordWithData(fresh);
        fresh.setId(-1);
        dao.delete(fresh);
    }


}