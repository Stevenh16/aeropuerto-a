package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.PassengerDto;
import model.entity.Passenger;
import model.mapper.PassengerMapper;
import model.repository.PassengerRepository;
import model.repository.ReserveRepository;
import model.service.PassengerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class PassengerServiceImpl implements PassengerService {
    private final PassengerMapper passengerMapper;
    private final ReserveRepository reserveRepository;
    PassengerRepository passengerRepository;

    @Override
    public PassengerDto save(PassengerDto passenger) {
        return passengerMapper.toIdDto(passengerRepository.save(passengerMapper.toEntity(passenger)));
    }

    @Override
    public Optional<PassengerDto> getById(Long id) {
        return passengerRepository.findById(id).map(passengerMapper::toIdDto);
    }

    @Override
    public Optional<PassengerDto> update(Long id, PassengerDto passenger) {
        return passengerRepository.findById(id).map(oldPassenger ->{
            oldPassenger.setName(passenger.name());
            oldPassenger.setLastname(passenger.lastname());
            oldPassenger.setAddress(passenger.address());
            oldPassenger.setCellphone(passenger.cellphone());
            oldPassenger.setEmail(passenger.email());
            oldPassenger.setReserve(reserveRepository.findById(passenger.reserve()).get());
            return passengerMapper.toIdDto(passengerRepository.save(oldPassenger));
        });
    }

    @Override
    public List<PassengerDto> findAll() {
        return passengerMapper.toListIdDto(passengerRepository.findAll());
    }

    @Override
    public List<PassengerDto> findByName(String name) {
        Passenger p = new Passenger();
        p.setName(name);
        Example<Passenger> example = Example.of(p);
        return passengerMapper.toListIdDto(passengerRepository.findAll(example));
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }
}
