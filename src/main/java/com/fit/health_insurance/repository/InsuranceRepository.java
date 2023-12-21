package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
    @Query(value = "SELECT INSURANCE.* FROM INSURANCE INNER JOIN INSURANCE_TYPE ON INSURANCE.INSURANCE_TYPE_ID = INSURANCE_TYPE.ID WHERE INSURANCE_TYPE.SLUG = :slug", nativeQuery = true)
    List<Insurance> findBySlug(@Param("slug") String slug);
    Optional<Insurance> findById(Integer id);
}
