package com.fit.health_insurance.insurance.repository;

import com.fit.health_insurance.insurance.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceReposity extends JpaRepository<Insurance, Integer> {

}
