package model.mapper;

import lombok.AllArgsConstructor;
import model.dto.ReserveDto;
import model.dto.ReserveIdDto;
import model.entity.Reserve;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ReserveMapper {
    private FlightMapper flightMapper;
    private PassengerMapper passengerMapper;
    private ClientMapper clientMapper;
    public Reserve toEntity(ReserveDto reserveDto){
        Reserve reserve = new Reserve();
        reserve.setNumbersOfPassengers(reserveDto.numbersOfPassengers());
        reserve.setDate(reserveDto.date());
        reserve.setFlights(flightMapper.toEntities(reserveDto.flights()));
        reserve.setNumbersOfPassengers(reserveDto.numbersOfPassengers());
        reserve.setPassengers(passengerMapper.toEntities(reserveDto.passengers()));
        reserve.setClient(clientMapper.toEntity(reserveDto.client()));
        return reserve;
    }
    public List<Reserve> toEntities(List<ReserveDto> reserveDtos){
        List<Reserve> reserveList = new ArrayList<>();
        for(ReserveDto dto : reserveDtos){
            reserveList.add(toEntity(dto));
        }
        return reserveList;
    }
    public ReserveDto toDto(Reserve reserve){
        return new ReserveDto(reserve.getDate(),reserve.getNumbersOfPassengers(),clientMapper.toDto(reserve.getClient()),passengerMapper.toDtos(reserve.getPassengers()),flightMapper.toDtos(reserve.getFlights()));
    }
    public List<ReserveDto> toDtos(List<Reserve> reserveList){
        List<ReserveDto> reserveDtoList = new ArrayList<>();
        for(Reserve reserve : reserveList){
            reserveDtoList.add(toDto(reserve));
        }
        return reserveDtoList;
    }
    public ReserveIdDto toIdDto(Reserve reserve){
        return new ReserveIdDto(reserve.getId(), reserve.getDate(), reserve.getNumbersOfPassengers(), clientMapper.toIdDto(reserve.getClient()), passengerMapper.toIdDtos(reserve.getPassengers()), flightMapper.toIdDtos(reserve.getFlights()));
    }
    public List<ReserveIdDto> toIdDtos(List<Reserve> reserveList){
        List<ReserveIdDto> reserveIdDtoList = new ArrayList<>();
        for(Reserve reserve : reserveList){
            reserveIdDtoList.add(toIdDto(reserve));
        }
        return reserveIdDtoList;
    }
}
