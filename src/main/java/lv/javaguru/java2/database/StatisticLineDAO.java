package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.StatisticLine;

import java.util.List;

public interface StatisticLineDAO {

    List<StatisticLine> getAll();
}
