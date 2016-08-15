package lv.javaguru.java2;

import lv.javaguru.java2.database.DBException;
import org.junit.Before;

public class IntegrationTest {

    private DatabaseCleaner cleaner = new DatabaseCleaner();

    @Before
    public void before() throws DBException {
        cleaner.cleanDatabase();
    }
}
