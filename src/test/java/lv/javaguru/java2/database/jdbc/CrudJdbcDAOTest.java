package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.database.CrudDAOTest;
import lv.javaguru.java2.domain.BaseEntity;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public abstract class CrudJdbcDAOTest<RecordClass extends BaseEntity, DAOClass extends CrudDAO<RecordClass>>
        extends CrudDAOTest<RecordClass, DAOClass> {

    @Test(expected = IllegalArgumentException.class)
    public void createWithNonZeroIdFails() {
        RecordClass fresh = newRecord();
        fresh.setId(123);
        dao.create(fresh);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithZeroFails() {
        RecordClass fresh = newRecord();
        dao.update(fresh);
    }

    @Test
    public void deleteNullDoNothing() {
        dao.delete(null);
    }

    @Test
    public void testCouldNotFindDeletedRecord() {
        long id = recordFromDAO.getId();
        dao.delete(recordFromDAO);
        assertEquals(0, recordFromDAO.getId());
        recordFromDAO = dao.getById(id);
        assertNull(recordFromDAO);
    }

    @Test
    public void deleteWithWrongIdFails() {
        RecordClass fresh = newRecord();
        fillRecordWithData(fresh);
        fresh.setId(-1);
        dao.delete(fresh);
    }
}
