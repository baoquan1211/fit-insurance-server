package com.fit.health_insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance_type")
public class InsuranceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String slug;
    private String description;
    private String image;
    private boolean isActive;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_type_id")
    private List<Insurance> insurances;
}
