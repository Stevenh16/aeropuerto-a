package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.AirlineDto;
import model.dto.AirlineIdDto;
import model.entity.Airline;
import model.mapper.AirlineMapper;
import model.mapper.FlightMapper;
import model.repository.AirlineRepository;
import model.service.AirlineServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineServices {
    private AirlineRepository airlineRepository;
    private FlightMapper flightMapper;
    private AirlineMapper airlineMapper;

    @Override
    public AirlineIdDto save(AirlineDto airline) {
        return airlineMapper.toIdDto(airlineRepository.save(airlineMapper.toEntity(airline)));
    }

    @Override
    public Optional<AirlineIdDto> findById(int id) {
        return Optional.of(airlineMapper.toIdDto(airlineRepository.findById(id).get()));
    }

    @Override
    public Optional<AirlineIdDto> update(int id, AirlineDto airline) {
        return Optional.of(airlineMapper.toIdDto( airlineRepository.findById(id).map(oldAirline -> {
            oldAirline.setAirlineCode(airline.airlineCode());
            oldAirline.setName(airline.name());
            oldAirline.setCountryOfOrigin(airline.countryOfOrigin());
            oldAirline.setFlights(flightMapper.toEntities(airline.flights()));//Preguntar!!! Los Flights a asignar no tendran los mismos ids que los originales, o eso creo.
            return airlineRepository.save(oldAirline);
        }).get() ));
    }

    @Override
    public List<AirlineIdDto> findAll() {
        return airlineMapper.toIdDtos(airlineRepository.findAll());
    }

    @Override
    public List<AirlineIdDto> findByName(String name) {
        Airline a = new Airline();
        a.setName(name);
        Example<Airline> example = Example.of(a);
        return airlineMapper.toIdDtos(airlineRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        airlineRepository.deleteById(id);
    }

}
