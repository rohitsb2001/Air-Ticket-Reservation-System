package com.crimsonlogic.airticketreservationsystem.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.airticketreservationsystem.entity.Passenger;
import com.crimsonlogic.airticketreservationsystem.repository.PassengerRepository;
@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Optional<Passenger> findPassengerById(BigInteger pnrNumber) {
        return passengerRepository.findById(pnrNumber);
    }

    @Override
    public List<Passenger> findAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public void deletePassenger(BigInteger pnrNumber) {
        passengerRepository.deleteById(pnrNumber);
    }
}
