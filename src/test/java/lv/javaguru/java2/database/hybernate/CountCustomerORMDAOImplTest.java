package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CountCustomerORMDAOImplTest {

    @Autowired
    DatabaseCleaner databaseCleaner;

    @Before
    public void before(){
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createCountCustomerTest(){

    }

    @Test
    public void updateCountCustomerTest(){

    }

    @Test
    public void getCountByProductTest(){

    }

    @Test
    public void getCountByCustomerTest(){

    }

    @Test
    public void getCountByProductAndVisitorTest(){

    }

    @Test
    public void getAllCountTest(){

    }

}
