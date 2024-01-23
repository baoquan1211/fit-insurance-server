package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.PayoutRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PayoutRequestRepository extends JpaRepository<PayoutRequest, Integer>{
    Optional<PayoutRequest> findById(Integer id);

    Page<PayoutRequest> findAll(Pageable pageable);

    @Query(value = "SELECT PAYOUT_REQUEST.* FROM PAYOUT_REQUEST INNER JOIN CONTRACT " +
            "ON PAYOUT_REQUEST.CONTRACT_ID = CONTRACT.ID INNER JOIN USERS ON CONTRACT.BUYER = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true)
    List<PayoutRequest> findAllByEmail(@Param("email") String email);

    @Query(value = "SELECT PAYOUT_REQUEST.* FROM PAYOUT_REQUEST INNER JOIN CONTRACT " +
            "ON PAYOUT_REQUEST.CONTRACT_ID = CONTRACT.ID AND PAYOUT_REQUEST.STATUS = 'ACCEPTED' INNER JOIN USERS ON CONTRACT.BUYER = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true)
    List<PayoutRequest> findAcceptedByEmail(@Param("email") String email);

    @Query(value = "SELECT PAYOUT_REQUEST.* FROM PAYOUT_REQUEST INNER JOIN CONTRACT " +
            "ON PAYOUT_REQUEST.CONTRACT_ID = CONTRACT.ID AND PAYOUT_REQUEST.STATUS = 'PENDING' INNER JOIN USERS ON CONTRACT.BUYER = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true)
    List<PayoutRequest> findPendingByEmail(@Param("email") String email);

    @Query(value = "SELECT PAYOUT_REQUEST.* FROM PAYOUT_REQUEST INNER JOIN CONTRACT " +
            "ON PAYOUT_REQUEST.CONTRACT_ID = CONTRACT.ID AND PAYOUT_REQUEST.STATUS = 'REJECTED' INNER JOIN USERS ON CONTRACT.BUYER = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true)
    List<PayoutRequest> findRejectedByEmail(@Param("email") String email);

    @Query(value = "SELECT SUM(TOTAL_PAY) FROM PAYOUT_REQUEST WHERE CONTRACT_ID = :contractId AND STATUS = 'ACCEPTED'", nativeQuery = true)
    Integer getTotalPayoutByContract(@Param("contractId") Integer contractId);
}
