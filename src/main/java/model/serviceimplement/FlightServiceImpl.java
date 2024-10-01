package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.FlightDto;
import model.dto.FlightIdDto;
import model.entity.Flight;
import model.mapper.AirlineMapper;
import model.mapper.AirportMapper;
import model.mapper.FlightMapper;
import model.mapper.ReserveMapper;
import model.repository.FlightRepository;
import model.service.FlightServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightServices {
    private final FlightMapper flightMapper;
    private final AirlineMapper airlineMapper;
    private final ReserveMapper reserveMapper;
    private final AirportMapper airportMapper;
    FlightRepository flightRepository;

    @Override
    public FlightIdDto save(FlightDto flight) {
        return flightMapper.toIdDto(flightRepository.save(flightMapper.toEntity(flight)));
    }

    @Override
    public Optional<FlightIdDto> findById(int id) {
        return Optional.of(flightMapper.toIdDto(flightRepository.findById(id).get()));
    }

    @Override
    public Optional<FlightIdDto> update(int id, FlightDto flight) {
        return Optional.of(flightMapper.toIdDto(flightRepository.findById(id).map(oldFlight ->{
            oldFlight.setExitTime(flight.exitTime());
            oldFlight.setAirline(airlineMapper.toEntity(flight.airline()));
            oldFlight.setReserves(reserveMapper.toEntities(flight.reserves()));
            oldFlight.setDuration(flight.duration());
            oldFlight.setCapacity(flight.capacity());
            oldFlight.setAirport_destination(airportMapper.toEntity(flight.airportDestination()));
            oldFlight.setAirport_origin(airportMapper.toEntity(flight.airportOrigin()));
            oldFlight.setExitDate(flight.exitDate());
            return flightRepository.save(oldFlight);
        }).get()));
    }

    @Override
    public List<FlightIdDto> findAll() {
        return flightMapper.toIdDtos(flightRepository.findAll());
    }

    @Override
    public List<FlightIdDto> findByDate(LocalDate date) {
        Flight f = new Flight();
        f.setExitDate(date);
        Example<Flight> example = Example.of(f);
        return flightMapper.toIdDtos(flightRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }
}
