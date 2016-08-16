package lv.javaguru.java2;

import org.junit.Before;

public class IntegrationTest {

    private DatabaseCleaner cleaner = new DatabaseCleaner();

    @Before
    public void setup() {
        cleaner.cleanDatabase();
    }
}
