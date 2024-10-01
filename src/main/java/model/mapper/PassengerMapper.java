package model.mapper;

import lombok.AllArgsConstructor;
import model.dto.PassengerDto;
import model.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PassengerMapper {
    private ReserveMapper reserveMapper;
    public Passenger toEntity(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setReserve(reserveMapper.toEntity(passengerDto.reserve()));
        passenger.setName(passengerDto.name());
        passenger.setAddress(passengerDto.address());
        passenger.setEmail(passengerDto.email());
        passenger.setCellphone(passengerDto.cellphone());
        passenger.setLastname(passengerDto.lastname());
        return passenger;
    }
    public List<Passenger> toEntities(List<PassengerDto> passengerDtos) {
        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDto passengerDto : passengerDtos) {
            passengers.add(toEntity(passengerDto));
        }
        return passengers;
    }
    public PassengerDto toDto(Passenger passenger) {
        return new PassengerDto(passenger.getName(), passenger.getLastname(), passenger.getAddress(), passenger.getCellphone(), passenger.getEmail(),reserveMapper.toDto(passenger.getReserve()));
    }
    public List<PassengerDto> toDtos(List<Passenger> passengers) {
        List<PassengerDto> passengerDtos = new ArrayList<>();
        for (Passenger passenger : passengers) {
            passengerDtos.add(toDto(passenger));
        }
        return passengerDtos;
    }
}
