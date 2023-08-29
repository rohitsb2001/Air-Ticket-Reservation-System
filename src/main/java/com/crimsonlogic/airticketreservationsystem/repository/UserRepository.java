package com.crimsonlogic.airticketreservationsystem.repository;
import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.crimsonlogic.airticketreservationsystem.entity.Users;

public interface UserRepository extends JpaRepository<Users, BigInteger>{

}