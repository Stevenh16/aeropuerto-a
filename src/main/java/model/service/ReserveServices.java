package model.service;

import model.entity.Reserve;

import java.util.List;
import java.util.Optional;

public interface ReserveServices {
    Reserve saveReserve(Reserve reserve);
    Optional<Reserve> findReserveById(int id);
    Optional<Reserve> updateReserve(int id, Reserve reserve);
    List<Reserve> findAllReserves();
    void deleteReserve(int id);
}