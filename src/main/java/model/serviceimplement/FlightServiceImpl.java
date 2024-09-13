package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.entity.Flight;
import model.repository.FlightRepository;
import model.service.FlightServices;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FlightServiceImpl implements FlightServices {
    FlightRepository flightRepository;

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> findFlightById(int id) {
        return flightRepository.findById(id);
    }

    @Override
    public Optional<Flight> updateFlight(int id, Flight flight) {
        Flight f = flightRepository.findById(id).get();
        f.setAirline(flight.getAirline());
        f.setDuration(flight.getDuration());
        f.setCapacity(flight.getCapacity());
        f.setExitDate(flight.getExitDate());
        f.setAirport_destination(flight.getAirport_destination());
        f.setAirport_origin(flight.getAirport_origin());
        f.setExitTime(flight.getExitTime());
        f.setReserves(flight.getReserves());
        return Optional.of(flightRepository.save(f));
    }

    @Override
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public void deleteFlightById(int id) {
        flightRepository.deleteById(id);
    }
}
