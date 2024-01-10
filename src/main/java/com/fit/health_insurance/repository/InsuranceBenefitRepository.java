package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.InsuranceBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceBenefitRepository extends JpaRepository<InsuranceBenefit, Integer> {
    Optional<InsuranceBenefit> findById(Integer id);
}
