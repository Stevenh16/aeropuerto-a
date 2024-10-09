package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.FlightDto;
import model.entity.Flight;
import model.mapper.AirlineMapper;
import model.mapper.AirportMapper;
import model.mapper.FlightMapper;
import model.mapper.ReserveMapper;
import model.repository.FlightRepository;
import model.service.FlightService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightMapper flightMapper;
    private final AirlineMapper airlineMapper;
    private final ReserveMapper reserveMapper;
    private final AirportMapper airportMapper;
    FlightRepository flightRepository;

    @Override
    public FlightDto save(FlightDto flight) {
        return flightMapper.toIdDto(flightRepository.save(flightMapper.toEntity(flight)));
    }

    @Override
    public Optional<FlightDto> findById(int id) {
        return Optional.of(flightMapper.toIdDto(flightRepository.findById(id).get()));
    }

    @Override
    public Optional<FlightDto> update(int id, FlightDto flight) {
        return flightRepository.findById(id).map(oldFlight ->{
            oldFlight.setExitTime(flight.exitTime());
            oldFlight.setAirline(airlineMapper.toEntity(flight.airline()));
            oldFlight.setReserves(reserveMapper.toListEntity(flight.reserves()));
            oldFlight.setDuration(flight.duration());
            oldFlight.setCapacity(flight.capacity());
            oldFlight.setAirport_destination(airportMapper.toEntity(flight.airportDestination()));
            oldFlight.setAirport_origin(airportMapper.toEntity(flight.airportOrigin()));
            oldFlight.setExitDate(flight.exitDate());
            return flightMapper.toIdDto(flightRepository.save(oldFlight));
        });
    }

    @Override
    public List<FlightDto> findAll() {
        return flightMapper.toListIdDto(flightRepository.findAll());
    }

    @Override
    public List<FlightDto> findByDate(LocalDate date) {
        Flight f = new Flight();
        f.setExitDate(date);
        Example<Flight> example = Example.of(f);
        return flightMapper.toListIdDto(flightRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }
}
