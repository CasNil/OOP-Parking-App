package se.lexicon.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;

import java.util.Collection;
import java.util.Optional;

public class CustomerDaoTest {

    private CustomerDao testObject;

    @BeforeEach
    public void setup() {
        testObject = new CustomerDaoImpl();

        Vehicle vehicle1 = new Vehicle("TES123", VehicleType.CAR);
        Reservation reservation1 = new Reservation(5, vehicle1, new ParkingSpot(34));
        Customer expectedCustomer = new Customer(1001, "John Doe", "073123456",(reservation1));
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
        Customer updatedCustomer = new Customer(1001, "John Doe", "073768429");
        testObject.update(updatedCustomer);
    }

    // todo: write test cases for CustomerDao
}
