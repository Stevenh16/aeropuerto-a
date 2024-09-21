package model.service;

import model.entity.Client;
import model.entity.Reserve;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface ReserveServices {
    Reserve saveReserve(Reserve reserve);
    Optional<Reserve> findReserveById(int id);
    Optional<Reserve> updateReserve(int id, Reserve reserve);
    List<Reserve> findAllReserves();
    List<Reserve> findByClient(Client client);
    List<Reserve> findByDate(LocalDate date);
    void deleteReserve(int id);
}
