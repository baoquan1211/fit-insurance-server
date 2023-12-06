package com.fit.health_insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@Table(name = "personal_document")
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

    public PersonalDocument() {
        this.createdAt = new Date();
    }

    public PersonalDocument(RegistrationForm formEntity) {
        this.registrationForm = formEntity;
        this.createdAt = new Date();
    }
}
