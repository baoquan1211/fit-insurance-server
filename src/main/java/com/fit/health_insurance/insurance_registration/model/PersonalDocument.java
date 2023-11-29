package com.fit.health_insurance.insurance_registration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personal_documents")
public class PersonalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Date createdAt;
    private String URL;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_form_id")
    private RegistrationForm registrationForm;

    public PersonalDocument(RegistrationForm formEntity) {
        this.registrationForm = formEntity;
    }
}
