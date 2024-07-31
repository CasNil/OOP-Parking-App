package se.lexicon.dao;

import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        for (ParkingSpot parkingSpotElement : parkingSpots) {
            if (parkingSpotElement.getSpotNumber() == parkingSpot.getSpotNumber() && parkingSpotElement.getAreaCode() == parkingSpot.getAreaCode()) {
                throw new IllegalArgumentException("Parking spot already exists");
            }
        }
        parkingSpots.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber, int areaCode) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getSpotNumber() == spotNumber && parkingSpot.getAreaCode() == areaCode) {
                return Optional.of(parkingSpot);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        List<ParkingSpot> foundSpots = new ArrayList<>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getAreaCode() == areaCode) {
                foundSpots.add(parkingSpot);
            }
        }
        return foundSpots;
    }

    @Override
    public void occupyParkingSpot(int spotNumber, int areaCode) {
        for (ParkingSpot parkingSpotElement : parkingSpots) {
            if (parkingSpotElement.getSpotNumber() == spotNumber && (parkingSpotElement.getAreaCode() == areaCode)) {
                parkingSpotElement.setSpotNumber(spotNumber);
                parkingSpotElement.setAreaCode(areaCode);
                parkingSpotElement.occupy();
                break;
            }
        }
    }

    @Override
    public void vacateParkingSpot(int spotNumber, int areaCode) {
        for (ParkingSpot parkingSpotElement : parkingSpots) {
            if (parkingSpotElement.getSpotNumber() == spotNumber && (parkingSpotElement.getAreaCode() == areaCode)) {
                parkingSpotElement.vacate();
                break;
            }
        }
    }
    // todo: implement ParkingSpotDao
}
