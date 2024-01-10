package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.HealthDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HealthDocumentRepository extends JpaRepository<HealthDocument, Integer> {
    @Query(value = "SELECT * FROM PERSONAL_DOCUMENT WHERE REGISTRATION_FORM_ID = :formID", nativeQuery = true)
    List<HealthDocument> findByRegistrationForm(@Param("formID") Integer formID);
}
