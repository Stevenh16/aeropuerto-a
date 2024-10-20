package model.security.service;

import model.entity.Passenger;
import model.repository.PassengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PassengerDetailsServiceImpl implements UserDetailsService {
    private PassengerRepository passengerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Passenger passenger = passengerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Passenger not found"));
        return PassengerDetailsImpl.build(passenger);
    }
}
