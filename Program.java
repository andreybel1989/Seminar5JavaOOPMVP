import java.util.Date;

import models.TableService;
import presenters.BookingPresenter;
import presenters.Model;
import views.BookingView;

public class Program {

    /**
     * TODO: ДОМАШНЯЯ РАБОТА
     * Метод changeReservationTable должен ЗАРАБОТАТЬ!
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Model model = new TableService();
        BookingView view = new BookingView();
        BookingPresenter presenter = new BookingPresenter(model, view);
        presenter.updateTablesView();
        view.reservationTable(new Date(), 1, "Олег");
        view.reservationTable(new Date(), 2, "Дмитрий");
        view.reservationTable(new Date(), 3, "Иван");
        view.reservationTable(new Date(), 4, "Оля");
        view.reservationTable(new Date(), 4, "Михаил");

        view.changeReservationTable(1002, new Date(), 3, "Станислав");
        view.changeReservationTable(1003, new Date(), 1, "Ирина");
        System.out.println();
        view.showAllReservations(model.loadTables());
    }

}