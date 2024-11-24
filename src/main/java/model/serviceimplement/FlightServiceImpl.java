package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.FlightDto;
import model.entity.Flight;
import model.mapper.FlightMapper;
import model.repository.AirlineRepository;
import model.repository.FlightRepository;
import model.service.FlightService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class FlightServiceImpl implements FlightService {
    private final FlightMapper flightMapper;
    private final AirlineRepository airlineRepository;
    FlightRepository flightRepository;

    @Override
    public FlightDto save(FlightDto flight) {
        return flightMapper.toIdDto(flightRepository.save(flightMapper.toEntity(flight)));
    }

    @Override
    public Optional<FlightDto> findById(Long id) {
        return flightRepository.findById(id).map(flightMapper::toIdDto);
    }

    @Override
    public Optional<FlightDto> update(Long id, FlightDto flight) {
        return flightRepository.findById(id).map(oldFlight ->{
            oldFlight.setExitTime(flight.exitTime());
            oldFlight.setAirline(airlineRepository.findById(flight.airline()).get());
            oldFlight.setReserves(flightMapper.mapToReserveList(flight.reserves()));
            oldFlight.setDuration(flight.duration());
            oldFlight.setCapacity(flight.capacity());
            oldFlight.setAirportDestination(flightMapper.mapToAirport(flight.airportDestination()));
            oldFlight.setAirportOrigin(flightMapper.mapToAirport(flight.airportOrigin()));
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
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }
}
