package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.InsuranceBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InsuranceBenefitRepository extends JpaRepository<InsuranceBenefit, Integer> {
    Optional<InsuranceBenefit> findById(Integer id);

    @Query(value = "SELECT * FROM INSURANCE_BENEFIT WHERE INSURANCE_ID = :insuranceId", nativeQuery = true)
    List<InsuranceBenefit> findAllByInsurance(@Param("insuranceId") Integer insuranceId);

    @Query(value = "SELECT * FROM INSURANCE_BENEFIT WHERE INSURANCE_ID = :insuranceId AND IS_ACTIVE = true", nativeQuery = true)
    List<InsuranceBenefit> findActiveByInsurance(@Param("insuranceId") Integer insuranceId);

    @Query(value = "SELECT * FROM INSURANCE_BENEFIT WHERE INSURANCE_ID = :insuranceId AND IS_ACTIVE = false", nativeQuery = true)
    List<InsuranceBenefit> findInActiveByInsurance(@Param("insuranceId") Integer insuranceId);
}
