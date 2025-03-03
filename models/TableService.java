package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import presenters.Model;

public class TableService implements Model {

    private Collection<Table> tables;

    @Override
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(table, reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }

    @Override
    public int changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        // for (Table table : tables) {
        // for (Reservation reservation : table.getReservations()) {
        // if (reservation.getId() == oldReservation) {
        // reservation.setDate(reservationDate);
        // reservation.setName(name);
        // return reservation.getId();
        // }
        // }
        // }
        // throw new RuntimeException("Бронирование с номером " + oldReservation + " не
        // найдено");
        // }

        boolean reservationExists = false;
        for (Table table : tables) {
            for (Reservation reservation : table.getReservations()) {
                if (reservation.getId() == oldReservation) {
                    table.getReservations().remove(reservation);
                    reservationExists = true;
                    break;
                }
            }
            if (reservationExists) {
                break;
            }
        }
        if (!reservationExists) {
            throw new RuntimeException("Бронирование с номером " + oldReservation + " ненайдено");
        }
        return reservationTable(reservationDate, tableNo, name);
    }
}
