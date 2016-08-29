package lv.javaguru.java2.database;


import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.domain.BaseEntity;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public abstract class CrudDAOTest<RecordClass extends BaseEntity, DAOClass extends CrudDAO<RecordClass>> {
    
}