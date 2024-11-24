package model.service;

import model.dto.ClientDto;
import model.dto.ReserveDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReserveService {
    ReserveDto save(ReserveDto reserve);
    Optional<ReserveDto> findById(Long id);
    Optional<ReserveDto> update(Long id, ReserveDto reserve);
    List<ReserveDto> findAll();
    List<ReserveDto> findByClient(ClientDto client);
    List<ReserveDto> findByDate(LocalDate date);
    void deleteById(Long id);
}
