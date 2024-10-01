package model.service;

import model.dto.ClientDto;
import model.dto.ReserveDto;
import model.dto.ReserveIdDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReserveServices {
    ReserveIdDto save(ReserveDto reserve);
    Optional<ReserveIdDto> findById(int id);
    Optional<ReserveIdDto> update(int id, ReserveDto reserve);
    List<ReserveIdDto> findAll();
    List<ReserveIdDto> findByClient(ClientDto client);
    List<ReserveIdDto> findByDate(LocalDate date);
    void deleteById(int id);
}
