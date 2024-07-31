package se.lexicon.dao;

import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private List<Reservation> reservationList = new ArrayList<>();

    @Override
    public Reservation create(Reservation reservation) {
        for (Reservation reservationElement : reservationList) {
            if (reservationElement.getId().equalsIgnoreCase(reservation.getId()) && reservationElement.getAssociatedVehicle().equals(reservation.getAssociatedVehicle())) {
                throw new IllegalArgumentException("You have already made a reservation");
            }
        }
        reservationList.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation reservation : reservationList) {
            if (reservation.getId().equalsIgnoreCase(id)) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }
    // todo: implement ReservationDao
}
