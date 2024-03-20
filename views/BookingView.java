package views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import models.Reservation;
import models.Table;
import presenters.View;
import presenters.ViewObserver;

public class BookingView implements View {

    private Collection<ViewObserver> observers;
    private String name;
    private int tableNo;

    public void showTables(Collection<Table> tables) {
        for (Table table : tables) {
            System.out.println(table);
        }
    }

    @Override
    public void registerObserver(ViewObserver observer) {
        if (observers == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }

    public void reservationTable(Date orderDate, int tableNo, String name) {
        if (observers != null) {
            this.name = name;
            this.tableNo = tableNo;
            for (ViewObserver observer : observers) {
                observer.onReservationTable(orderDate, tableNo, name);
            }

        }
    }

    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        if (observers != null) {
            this.name = name;
            this.tableNo = tableNo;
            for (ViewObserver observer : observers) {
                observer.onChangeReservationTable(oldReservation, reservationDate, tableNo, name);
            }

        }
    }

    @Override
    public void showReservationTableResult(int reservationNo) {

        if (reservationNo > 0) {
            System.out.printf("Столик успешно забронирован. Номер брони: #%d, Номер стола: #%d, Имя: %s\n",
                    reservationNo, tableNo, name);
        } else {
            System.out.println("Произошла ошибка при попытке забронироватьстолик.\n    Повторите операцию позже.");
        }
    }

    @Override
    public void showAllReservations(Collection<Table> tables) {

        for (Table table : tables) {

            for (Reservation reservation : table.getReservations()) {
                System.out.printf(
                        "Бронирования для столика #%d: Номер бронирования: #%d, Дата бронирования: %s: Номер стола: #%d, Имя: %s\n",
                        table.getNo(), reservation.getId(), reservation.getDate(), table.getNo(),
                        reservation.getName());

            }
        }

    }

}