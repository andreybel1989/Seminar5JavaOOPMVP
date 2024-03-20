package presenters;

import java.util.Collection;
import java.util.Date;

import models.Table;

public interface Model {

    Collection<Table> loadTables();

    int reservationTable(Date reservationDate, int tableNo, String name);

}
