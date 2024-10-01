package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.ClientDto;
import model.dto.ReserveDto;
import model.dto.ReserveIdDto;
import model.entity.Reserve;
import model.mapper.ClientMapper;
import model.mapper.FlightMapper;
import model.mapper.PassengerMapper;
import model.mapper.ReserveMapper;
import model.repository.ReserveRepository;
import model.service.ReserveServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveServices {
    private final ReserveMapper reserveMapper;
    private final ClientMapper clientMapper;
    private final FlightMapper flightMapper;
    private final PassengerMapper passengerMapper;
    ReserveRepository reserveRepository;

    @Override
    public ReserveIdDto save(ReserveDto reserve) {
        return reserveMapper.toIdDto(reserveRepository.save(reserveMapper.toEntity(reserve)));
    }

    @Override
    public Optional<ReserveIdDto> findById(int id) {
        return Optional.of(reserveMapper.toIdDto(reserveRepository.findById(id).get()));
    }

    @Override
    public Optional<ReserveIdDto> update(int id, ReserveDto reserve) {
        return Optional.of(reserveMapper.toIdDto(reserveRepository.findById(id).map(oldReserve -> {
            oldReserve.setClient(clientMapper.toEntity(reserve.client()));
            oldReserve.setDate(reserve.date());
            oldReserve.setFlights(flightMapper.toEntities(reserve.flights()));
            oldReserve.setPassengers(passengerMapper.toEntities(reserve.passengers()));
            oldReserve.setNumbersOfPassengers(reserve.numbersOfPassengers());
            return reserveRepository.save(oldReserve);
        }).get()));
    }

    @Override
    public List<ReserveIdDto> findAll() {
        return reserveMapper.toIdDtos(reserveRepository.findAll());
    }

    @Override
    public List<ReserveIdDto> findByClient(ClientDto client) {
        Reserve r = new Reserve();
        r.setClient(clientMapper.toEntity(client));
        Example<Reserve> example = Example.of(r);
        return reserveMapper.toIdDtos(reserveRepository.findAll(example));
    }

    @Override
    public List<ReserveIdDto> findByDate(LocalDate date) {
        Reserve r = new Reserve();
        r.setDate(date);
        Example<Reserve> example = Example.of(r);
        return reserveMapper.toIdDtos(reserveRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        reserveRepository.deleteById(id);
    }


}
