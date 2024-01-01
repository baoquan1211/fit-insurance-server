package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    @Query(value = "SELECT * FROM WARD WHERE DISTRICT_ID = :id" , nativeQuery = true)
    List<Ward> findAllByDistrictId(@Param("id") Integer id);
    Optional<Ward> findById(@NonNull Integer id);
}
