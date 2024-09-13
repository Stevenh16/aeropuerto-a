package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.entity.Airport;
import model.repository.AirportRepository;
import model.service.AirportServices;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AirportServiceImpl implements AirportServices {
    private AirportRepository airportRepository;

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Optional<Airport> findAirportById(int id) {
        return airportRepository.findById((long)id);
    }

    @Override
    public Optional<Airport> updateAirport(int id, Airport airport) {
        Airport a = airportRepository.findById((long)id).get();
        a.setCity(airport.getCity());
        a.setName(airport.getName());
        a.setCountry(airport.getCountry());
        a.setFlightsOrigins(airport.getFlightsOrigins());
        a.setFlightsDestinations(airport.getFlightsDestinations());
        return Optional.of(airportRepository.save(a));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public void deleteAirport(int id) {
        airportRepository.deleteById((long)id);
    }
}
