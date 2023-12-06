package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Integer> {
    public List<InsuranceType> findAll();
    public InsuranceType findBySlug(String slug);
}
