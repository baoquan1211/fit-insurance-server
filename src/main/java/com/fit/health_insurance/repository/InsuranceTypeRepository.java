package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Integer> {
    InsuranceType findBySlug(String slug);
}
