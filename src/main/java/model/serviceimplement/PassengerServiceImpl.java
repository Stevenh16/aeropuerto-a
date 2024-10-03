package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.PassengerDto;
import model.entity.Passenger;
import model.mapper.PassengerMapper;
import model.mapper.ReserveMapper;
import model.repository.PassengerRepository;
import model.service.PassengerServices;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerServices {
    private final PassengerMapper passengerMapper;
    private final ReserveMapper reserveMapper;
    PassengerRepository passengerRepository;

    @Override
    public PassengerDto save(PassengerDto passenger) {
        return passengerMapper.toIdDto(passengerRepository.save(passengerMapper.toEntity(passenger)));
    }

    @Override
    public Optional<PassengerDto> getById(int id) {
        return Optional.of(passengerMapper.toIdDto(passengerRepository.findById(id).get()));
    }

    @Override
    public Optional<PassengerDto> update(int id, PassengerDto passenger) {
        return Optional.of(passengerMapper.toIdDto(passengerRepository.findById(id).map(oldPassenger ->{
            oldPassenger.setName(passenger.name());
            oldPassenger.setLastname(passenger.lastname());
            oldPassenger.setAddress(passenger.address());
            oldPassenger.setCellphone(passenger.cellphone());
            oldPassenger.setEmail(passenger.email());
            oldPassenger.setReserve(reserveMapper.toEntity(passenger.reserve()));
            return passengerRepository.save(oldPassenger);
        }).get()));
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
    public void deleteById(int id) {
        passengerRepository.deleteById(id);
    }
}
