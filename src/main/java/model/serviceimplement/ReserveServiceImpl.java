package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.ClientDto;
import model.dto.ReserveDto;
import model.entity.Reserve;
import model.mapper.ClientMapper;
import model.mapper.FlightMapper;
import model.mapper.PassengerMapper;
import model.mapper.ReserveMapper;
import model.repository.ReserveRepository;
import model.service.ReserveService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final ReserveMapper reserveMapper;
    private final ClientMapper clientMapper;
    private final FlightMapper flightMapper;
    private final PassengerMapper passengerMapper;
    ReserveRepository reserveRepository;

    @Override
    public ReserveDto save(ReserveDto reserve) {
        return reserveMapper.toIdDto(reserveRepository.save(reserveMapper.toEntity(reserve)));
    }

    @Override
    public Optional<ReserveDto> findById(int id) {
        return Optional.of(reserveMapper.toIdDto(reserveRepository.findById(id).get()));
    }

    @Override
    public Optional<ReserveDto> update(int id, ReserveDto reserve) {
        return reserveRepository.findById(id).map(oldReserve -> {
            oldReserve.setClient(clientMapper.toEntity(reserve.client()));
            oldReserve.setDate(reserve.date());
            oldReserve.setFlights(flightMapper.toListEntity(reserve.flights()));
            oldReserve.setPassengers(passengerMapper.toListEntity(reserve.passengers()));
            oldReserve.setNumbersOfPassengers(reserve.numbersOfPassengers());
            return reserveMapper.toIdDto(reserveRepository.save(oldReserve));
        });
    }

    @Override
    public List<ReserveDto> findAll() {
        return reserveMapper.toListIdDto(reserveRepository.findAll());
    }

    @Override
    public List<ReserveDto> findByClient(ClientDto client) {
        Reserve r = new Reserve();
        r.setClient(clientMapper.toEntity(client));
        Example<Reserve> example = Example.of(r);
        return reserveMapper.toListIdDto(reserveRepository.findAll(example));
    }

    @Override
    public List<ReserveDto> findByDate(LocalDate date) {
        Reserve r = new Reserve();
        r.setDate(date);
        Example<Reserve> example = Example.of(r);
        return reserveMapper.toListIdDto(reserveRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        reserveRepository.deleteById(id);
    }


}
