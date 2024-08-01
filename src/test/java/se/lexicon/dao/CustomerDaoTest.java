package se.lexicon.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoTest {


    private CustomerDao testObject;
    private List<Customer> storage;

    @BeforeEach
    public void setup() {
        testObject = new CustomerDaoImpl();
        storage = new ArrayList<>();

        Vehicle vehicle1 = new Vehicle("TES123", VehicleType.CAR);
        Reservation reservation1 = new Reservation(5, vehicle1, new ParkingSpot(34));
        Customer expectedCustomer = new Customer(1001, "John Doe", "073123456", (reservation1));
        storage.add(expectedCustomer);

        Customer actualCustomer = testObject.create(expectedCustomer);
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer.getId(), actualCustomer.getId());
        Assertions.assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void testFind_existingCustomer() {
        Optional<Customer> optionalCustomer = testObject.find(1001);
        Assertions.assertTrue(optionalCustomer.isPresent());
    }

    @Test
    public void testFind_nonExistingCustomer() {
        Optional<Customer> optionalCustomer = testObject.find(1002);
        Assertions.assertFalse(optionalCustomer.isPresent());
    }

    @Test
    public void testRemove_existingCustomer() {
        boolean result = testObject.remove(1001);
        Assertions.assertTrue(result);
    }

    @Test
    public void testRemove_nonExistingCustomer() {
        boolean result = testObject.remove(1003);
        Assertions.assertFalse(result);
    }

    @Test
    public void testUpdate_existingCustomer() {
        Customer updatedCustomer = new Customer(1001, "John Doe", "073768428", new Reservation(5, new Vehicle("GKO874", VehicleType.CAR), new ParkingSpot(44)));
        testObject.update(updatedCustomer);

        Customer storedCustomer = storage.get(0);
        Assertions.assertEquals("John Doe", storedCustomer.getName());
        Assertions.assertEquals("073768428", storedCustomer.getPhoneNumber());
        Assertions.assertEquals(updatedCustomer.getReservation(), storedCustomer.getReservation());

    }

    @Test
    public void testUpdate_nonExistingCustomer() {
        ParkingSpot parkingSpot1 = new ParkingSpot(34);
        Customer nonExistingCustomer = new Customer(1002, "Joe Smalls", "076767528", new Reservation(5, new Vehicle("GKO874", VehicleType.CAR), new ParkingSpot(44)));
        testObject.update(nonExistingCustomer);

        Assertions.assertEquals(1, storage.size());
        Customer storedCustomer = storage.get(0);
        Assertions.assertEquals("John Doe", storedCustomer.getName());
        Assertions.assertEquals("073123456", storedCustomer.getPhoneNumber());
        Assertions.assertEquals(new Reservation(5, new Vehicle("TES123", VehicleType.CAR), parkingSpot1), storedCustomer.getReservation());
    }
    // todo: write test cases for CustomerDao
}
