package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.AirportDto;
import model.entity.Airport;
import model.mapper.AirportMapper;
import model.mapper.FlightMapper;
import model.repository.AirportRepository;
import model.service.AirportService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {
    private AirportMapper airportMapper;
    private FlightMapper flightMapper;
    private AirportRepository airportRepository;

    @Override
    public AirportDto save(AirportDto airport) {
        return airportMapper.toDto(airportRepository.save(airportMapper.toEntity(airport)));
    }

    @Override
    public Optional<AirportDto> findById(int id) {
        return airportRepository.findById((long) id).map(airportMapper::toIdDto);
    }

    @Override
    public Optional<AirportDto> update(int id, AirportDto airport) {
        return airportRepository.findById((long) id).map(oldAirport -> {
            oldAirport.setCity(airport.city());
            oldAirport.setCountry(airport.country());
            oldAirport.setName(airport.name());
            oldAirport.setFlightsOrigins(flightMapper.toListEntity(airport.flightsOrigins()));
            oldAirport.setFlightsDestinations(flightMapper.toListEntity(airport.flightsDestinations()));
            return airportMapper.toIdDto(airportRepository.save(oldAirport));
        });
    }

    @Override
    public List<AirportDto> findAll() {
        return airportMapper.toListDto(airportRepository.findAll());
    }

    @Override
    public List<AirportDto> findByName(String name) {
        Airport a = new Airport();
        a.setName(name);
        Example<Airport> example = Example.of(a);
        return airportMapper.toListDto(airportRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        airportRepository.deleteById((long)id);
    }

}
