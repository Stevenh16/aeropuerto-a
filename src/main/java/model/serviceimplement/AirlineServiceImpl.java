package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.entity.Airline;
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

    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Optional<Airline> findAirlineById(int id) {
        return airlineRepository.findById(id);
    }

    @Override
    public Optional<Airline> updateAirline(int id, Airline airline) {
        Airline a = airlineRepository.findById(id).get();
        a.setName(airline.getName());
        a.setAirlineCode(airline.getAirlineCode());
        a.setFlights(airline.getFlights());
        a.setCountryOfOrigin(airline.getCountryOfOrigin());
        return Optional.of(airlineRepository.save(a));
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public List<Airline> findByName(String name) {
        Airline a = new Airline();
        a.setName(name);
        Example<Airline> example = Example.of(a);
        return airlineRepository.findAll(example);
    }

    @Override
    public void deleteById(int id) {
        airlineRepository.deleteById(id);
    }

}
