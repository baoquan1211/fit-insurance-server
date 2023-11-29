package com.fit.health_insurance.insurance_registration.repository;

import com.fit.health_insurance.insurance_registration.model.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Integer> {
    @Query(value = "SELECT TOKEN FROM REGISTRATION_FORMS AS F INNER JOIN USERS AS U" +
            "ON F.REGISTRATOR_ID = U.ID WHERE U.ID = :id", nativeQuery = true
    )
    List<RegistrationForm> findAllByRegistrator(@Param("id") Integer id);
    List<RegistrationForm> findByIdentityCard(String identityCard);
}
