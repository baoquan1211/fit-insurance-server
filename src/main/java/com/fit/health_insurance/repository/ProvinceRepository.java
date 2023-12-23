package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findAll();
    Optional<Province> findById(Integer id);
}
