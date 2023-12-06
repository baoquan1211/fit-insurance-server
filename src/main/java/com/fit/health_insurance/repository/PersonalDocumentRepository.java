package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Integer> {
    @Query(value = "SELECT * FROM PERSONAL_DOCUMENT WHERE REGISTRATION_FORM_ID = :formID", nativeQuery = true)
    List<PersonalDocument> findByRegistrationForm(@Param("formID") Integer formID);
}
