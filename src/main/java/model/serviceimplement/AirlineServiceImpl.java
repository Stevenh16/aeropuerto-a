package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.AirlineDto;
import model.entity.Airline;
import model.mapper.AirlineMapper;
import model.mapper.FlightMapper;
import model.repository.AirlineRepository;
import model.service.AirlineService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private AirlineRepository airlineRepository;
    private FlightMapper flightMapper;
    private AirlineMapper airlineMapper;

    @Override
    public AirlineDto save(AirlineDto airline) {
        return airlineMapper.toIdDto(airlineRepository.save(airlineMapper.toEntity(airline)));
    }

    @Override
    public Optional<AirlineDto> findById(int id) {
        return airlineRepository.findById(id).map(airlineMapper::toIdDto);
    }

    @Override
    public Optional<AirlineDto> update(int id, AirlineDto airline) {
        return airlineRepository.findById(id).map(oldAirline -> {
            oldAirline.setAirlineCode(airline.airlineCode());
            oldAirline.setName(airline.name());
            oldAirline.setCountryOfOrigin(airline.countryOfOrigin());
            oldAirline.setFlights(flightMapper.toListEntity(airline.flights()));//Preguntar!!! Los Flights a asignar no tendran los mismos ids que los originales, o eso creo.
            return airlineMapper.toIdDto(airlineRepository.save(oldAirline));
        });
    }

    @Override
    public List<AirlineDto> findAll() {
        return airlineMapper.toListIdDto(airlineRepository.findAll());
    }

    @Override
    public List<AirlineDto> findByName(String name) {
        Airline a = new Airline();
        a.setName(name);
        Example<Airline> example = Example.of(a);
        return airlineMapper.toListIdDto(airlineRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        airlineRepository.deleteById(id);
    }

}
