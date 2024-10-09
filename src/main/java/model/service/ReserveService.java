package model.service;

import model.dto.ClientDto;
import model.dto.ReserveDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReserveService {
    ReserveDto save(ReserveDto reserve);
    Optional<ReserveDto> findById(int id);
    Optional<ReserveDto> update(int id, ReserveDto reserve);
    List<ReserveDto> findAll();
    List<ReserveDto> findByClient(ClientDto client);
    List<ReserveDto> findByDate(LocalDate date);
    void deleteById(int id);
}
