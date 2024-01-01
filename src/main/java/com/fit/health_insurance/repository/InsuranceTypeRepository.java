package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Integer> {
    Optional<InsuranceType> findBySlug(String slug);
    Optional<InsuranceType> findById(Integer id);
}
