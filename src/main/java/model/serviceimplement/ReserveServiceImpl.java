package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.entity.Client;
import model.entity.Reserve;
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
    ReserveRepository reserveRepository;

    @Override
    public Reserve saveReserve(Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    @Override
    public Optional<Reserve> findReserveById(int id) {
        return reserveRepository.findById(id);
    }

    @Override
    public Optional<Reserve> updateReserve(int id, Reserve reserve) {
        Reserve r = reserveRepository.findById(id).get();
        r.setClient(reserve.getClient());
        r.setDate(reserve.getDate());
        r.setFlights(reserve.getFlights());
        r.setPassengers(reserve.getPassengers());
        r.setNumbersOfPassengers(reserve.getNumbersOfPassengers());
        return Optional.of(reserveRepository.save(r));
    }

    @Override
    public List<Reserve> findAllReserves() {
        return reserveRepository.findAll();
    }

    @Override
    public List<Reserve> findByClient(Client client) {
        Reserve r = new Reserve();
        r.setClient(client);
        Example<Reserve> example = Example.of(r);
        return reserveRepository.findAll(example);
    }

    @Override
    public List<Reserve> findByDate(LocalDate date) {
        Reserve r = new Reserve();
        r.setDate(date);
        Example<Reserve> example = Example.of(r);
        return reserveRepository.findAll(example);
    }

    @Override
    public void deleteReserve(int id) {
        reserveRepository.deleteById(id);
    }


}
