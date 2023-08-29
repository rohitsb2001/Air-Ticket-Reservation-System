package com.crimsonlogic.airticketreservationsystem.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.airticketreservationsystem.entity.Passenger;
import com.crimsonlogic.airticketreservationsystem.service.PassengerService;


@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/createPassenger")
    public void createPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
    }

    @GetMapping("/findPassengerById/{pnrNumber}")
    public Optional<Passenger> findPassengerById(@PathVariable("pnrNumber") BigInteger pnrNumber) {
        return passengerService.findPassengerById(pnrNumber);
    }

    @GetMapping("/findAllPassengers")
    public List<Passenger> findAllPassengers() {
        return passengerService.findAllPassengers();
    }

    @DeleteMapping("/deletePassenger/{pnrNumber}")
    public void deletePassenger(@PathVariable("pnrNumber") BigInteger pnrNumber) {
        passengerService.deletePassenger(pnrNumber);
    }
}
