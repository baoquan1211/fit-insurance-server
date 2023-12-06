package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Integer> {
    @Query(value = "SELECT F.* FROM REGISTRATION_FORM AS F INNER JOIN USERS AS U" +
            "ON F.REGISTRATOR_ID = U.ID WHERE U.ID = :id", nativeQuery = true
    )
    List<RegistrationForm> findAllByRegistrator(@Param("id") Integer id);
    List<RegistrationForm> findAllByIdentityCard(String identityCard);

    @Query(value = "SELECT REGISTRATION_FORM.* FROM REGISTRATION_FORM INNER JOIN USERS " +
            "ON REGISTRATION_FORMS.REGISTRATOR_ID = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true
    )
    List<RegistrationForm> findAllByEmail(@Param("email") String email);
}
