package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "SELECT * FROM DISTRICT WHERE PROVINCE_ID = :id", nativeQuery = true)
    List<District> findAllByProvinceId(@Param("id") Integer id);
}
