package com.fit.health_insurance.insurance_registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRegistrationRepository extends JpaRepository<RegistrationForm, Integer> {
    @Query(value = "SELECT TOKEN FROM REGISTRATION_FORMS AS F INNER JOIN USERS AS U" +
            "ON F.REGISTRATOR_ID = U.ID" +
            "WHERE U.ID = :id", nativeQuery = true
    )
    List<RegistrationForm> findAllRegistrationByUser(Integer id);
}
