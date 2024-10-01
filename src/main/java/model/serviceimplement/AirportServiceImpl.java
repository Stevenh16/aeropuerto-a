package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.AirportDto;
import model.dto.AirportIdDto;
import model.entity.Airport;
import model.mapper.AirportMapper;
import model.mapper.FlightMapper;
import model.repository.AirportRepository;
import model.service.AirportServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportServices {
    private final AirportMapper airportMapper;
    private final FlightMapper flightMapper;
    private AirportRepository airportRepository;

    @Override
    public AirportIdDto save(AirportDto airport) {
        return airportMapper.toIdDto(airportRepository.save(airportMapper.toEntity(airport)));
    }

    @Override
    public Optional<AirportIdDto> findById(int id) {
        return Optional.of(airportMapper.toIdDto(airportRepository.findById((long) id).get()));
    }

    @Override
    public Optional<AirportIdDto> update(int id, AirportDto airport) {
        return Optional.of(airportMapper.toIdDto( airportRepository.findById((long) id).map(oldAirport -> {
            oldAirport.setCity(airport.city());
            oldAirport.setCountry(airport.country());
            oldAirport.setName(airport.name());
            oldAirport.setFlightsOrigins(flightMapper.toEntities(airport.flightsOrigins()));
            oldAirport.setFlightsDestinations(flightMapper.toEntities(airport.flightsDestinations()));
            return airportRepository.save(oldAirport);
        }).get()));
    }

    @Override
    public List<AirportIdDto> findAll() {
        return airportMapper.toIdDtos(airportRepository.findAll());
    }

    @Override
    public List<AirportIdDto> findByName(String name) {
        Airport a = new Airport();
        a.setName(name);
        Example<Airport> example = Example.of(a);
        return airportMapper.toIdDtos(airportRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        airportRepository.deleteById((long)id);
    }

}
