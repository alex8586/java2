package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class StatisticLineORMDAOImplTest {

    @Autowired
    StatisticLineORMDAOImpl statisticLineORMDAO;

    @Test
    public void testAll() {
        System.out.println(statisticLineORMDAO);
        System.out.println(statisticLineORMDAO.getAll().size());
    }
}