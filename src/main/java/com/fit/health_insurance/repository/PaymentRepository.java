package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Optional<Payment> findAllById(Integer id);

    List<Payment> findAll();
}
