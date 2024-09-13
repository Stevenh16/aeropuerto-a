package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.entity.Passenger;
import model.repository.PassengerRepository;
import model.service.PassengerServices;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PassengerServiceImpl implements PassengerServices {
    PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Optional<Passenger> getPassengerById(int id) {
        return passengerRepository.findById(id);
    }

    @Override
    public Optional<Passenger> updatePassenger(int id, Passenger passenger) {
        Passenger p = passengerRepository.findById(id).get();
        p.setAddress(passenger.getAddress());
        p.setName(passenger.getName());
        p.setCellphone(passenger.getCellphone());
        p.setEmail(passenger.getEmail());
        p.setLastname(passenger.getLastname());
        p.setReserve(passenger.getReserve());
        return Optional.of(passengerRepository.save(p));
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public void deletePassenger(int id) {
        passengerRepository.deleteById(id);
    }
}
