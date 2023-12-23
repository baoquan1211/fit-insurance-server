package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query(value = "SELECT CONTRACT.* FROM CONTRACT INNER JOIN USERS " +
            "ON CONTRACT.CREATED_BY = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true)
    List<Contract> findAllByEmail(@Param("email") String email);

    @Query(value = "SELECT CONTRACT.* FROM CONTRACT INNER JOIN USERS " +
            "ON CONTRACT.CREATED_BY = USERS.ID WHERE USERS.IDENTITYCARD = : identityCard", nativeQuery = true)
    List<Contract> findAllByIdentityCard(@Param("identityCard") String identityCard);

}
