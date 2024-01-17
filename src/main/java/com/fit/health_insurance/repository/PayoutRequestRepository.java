package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.PayoutRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PayoutRequestRepository extends JpaRepository<PayoutRequest, Integer> {
    Optional<PayoutRequest> findById(Integer id);

    @Query(value = "SELECT SUM(TOTAL_PAY) FROM PAYOUT_REQUEST WHERE CONTRACT_ID = :contractId AND STATUS = 'ACCEPTED'", nativeQuery = true)
    Integer getTotalPayoutByContract(@Param("contractId") Integer contractId);
}
