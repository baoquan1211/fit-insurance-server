package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.PayoutRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRequestRepository extends JpaRepository<PayoutRequest, Integer> {

}
