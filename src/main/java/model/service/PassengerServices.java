package model.service;

import model.entity.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerServices {
    Passenger savePassenger(Passenger passenger);
    Optional<Passenger> getPassengerById(int id);
    Optional<Passenger> updatePassenger(int id, Passenger passenger);
    List<Passenger> getAllPassengers();
    void deletePassenger(int id);
}
